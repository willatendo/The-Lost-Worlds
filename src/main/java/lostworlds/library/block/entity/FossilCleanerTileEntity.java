package lostworlds.library.block.entity;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.FossilCleanerBlock;
import lostworlds.library.container.FossilCleanerContainer;
import lostworlds.library.container.recipes.FossilCleanerRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class FossilCleanerTileEntity extends TileEntity implements IInventory, INamedContainerProvider, INameable, ITickableTileEntity	
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
	
	private int onTime;
	private int onDuration;
	public int cleaningProgress;
	private int cleaningTotalTime;
	
	protected final IIntArray cleanerData = new IIntArray()
	{
		@Override
		public int get(int index)
		{
			switch(index)
			{
				case 0:
					return onTime;
				case 1:
					return onDuration;
				case 2:
					return cleaningProgress;
				case 3:
					return cleaningTotalTime;
				default:
					return 0;
			}
		}
		
		@Override
		public void set(int index, int value)
		{
			switch(index)
			{
				case 0:
					onTime = value;
					break;
				case 1:
					onDuration = value;
					break;
				case 2:
					cleaningProgress = value;
					break;
				case 3:
					cleaningTotalTime = value;
					break;
			}
		}
		
		@Override
		public int getCount()
		{
			return 4;
		}
	};
	
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final IRecipeType<FossilCleanerRecipe> recipeType = RecipeInit.FOSSIL_CLEANER_RECIPE;
	
	public static Map<Item, Integer> getFuel() 
	{
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		add(map, Items.WATER_BUCKET, 3500);
		return map;
	}
	
	private static void add(Map<Item, Integer> map, IItemProvider itemProvider, int length) 
	{
		Item item = itemProvider.asItem();
		
		map.put(item, length);
	}

	private ITextComponent name;

	public FossilCleanerTileEntity() 
	{
		super(TileEntityInit.FOSSIL_CLEANER_TILE_ENTITY);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.cleaningProgress = nbt.getInt("CleanTime");
		this.cleaningTotalTime = nbt.getInt("CleanTimeTotal");
		this.onDuration = this.getCleanDuration(this.items.get(1));
		if(nbt.contains("CustomName", 8)) 
		{
			this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("CleanTime", this.cleaningProgress);
		nbt.putInt("CleanTimeTotal", this.cleaningTotalTime);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}
	
	public IIntArray getCleanerData()
	{
		return this.cleanerData;
	}
	
	public boolean isOn() 
	{
		return this.onTime > 0;
	}
	
	@Override
	public void tick() 
	{	
		boolean flag = this.isOn();
		boolean flag1 = false;
		if(this.isOn()) 
		{
			--this.onTime;
		}
		
		if(!this.level.isClientSide) 
		{
			if(this.level.hasNeighborSignal(this.getBlockPos()))
			{
				ItemStack fuel = this.items.get(1);
				if(this.isOn() || !fuel.isEmpty() && !this.items.get(0).isEmpty()) 
				{
					IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<FossilCleanerRecipe>)this.recipeType, this, this.level).orElse(null);
					if(!this.isOn() && this.canClean(irecipe)) 
					{
						this.onTime = this.getCleanDuration(fuel);
						this.onDuration = this.onTime;
						if(this.isOn()) 
						{
							flag1 = true;
							if(fuel.hasContainerItem())
								this.items.set(1, fuel.getContainerItem());
							else
								if(!fuel.isEmpty()) 
								{
									fuel.shrink(1);
									if(fuel.isEmpty()) 
									{
										this.items.set(1, fuel.getContainerItem());
									}
								}
						}
					}
						
					if(this.isOn() && this.canClean(irecipe)) 
					{
						++this.cleaningProgress;
						if(this.cleaningProgress == this.cleaningTotalTime) 
						{
							this.cleaningProgress = 0;
							this.cleaningTotalTime = 1000;
							this.clean(irecipe);
							flag1 = true;
						}
					} 
					else 
					{
						this.cleaningProgress = 0;
					}
				}
				else if(!this.isOn() && this.cleaningProgress > 0) 
				{
					this.cleaningProgress = MathHelper.clamp(this.cleaningProgress - 2, 0, this.cleaningTotalTime);
				}
				
				if(flag != this.isOn()) 
				{
					flag1 = true;
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FossilCleanerBlock.ON, Boolean.valueOf(this.isOn())), 3);
				}
			}
			
			if(flag1) 
			{
				this.setChanged();
			}
		}		
	}
	
	protected boolean canClean(@Nullable IRecipe<?> recipe) 
	{
		if(!this.items.get(0).isEmpty() && recipe != null) 
		{
			ItemStack itemstack = recipe.getResultItem();
			if(itemstack.isEmpty()) 
			{
				return false;
			} 
			else 
			{
				ItemStack itemstack1 = this.items.get(1);
				if(itemstack1.isEmpty()) 
				{
					return true;
				}
				else if(!itemstack1.sameItem(itemstack)) 
				{
					return false;
				} 
				else if(itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) 
				{
					return true;
				} 
				else 
				{
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
				}
			}
		} 
		else 
		{
			return false;
		}
	}
	
	private void clean(@Nullable IRecipe<?> recipe) 
	{
		if(recipe != null && this.canClean(recipe)) 
		{
			ItemStack fossil = this.items.get(0);
			ItemStack result = recipe.getResultItem();
			ItemStack output = this.items.get(1);
			if(output.isEmpty()) 
			{
				this.items.set(1, result.copy());
			}
			else if(output.getItem() == result.getItem()) 
			{
				output.grow(result.getCount());
			}
			
			if(!this.level.isClientSide) 
			{
				this.setRecipeUsed(recipe);
			}
			
			fossil.shrink(1);
		}
	}
	
	protected int getCleanDuration(ItemStack stack) 
	{
		if(stack.isEmpty())
		{
			return 0;
		}
		else
		{
			Item item = stack.getItem();
			return getFuel().getOrDefault(item, 0);
		}
	}
	
	@Override
	public int getContainerSize() 
	{
		return this.items.size();
	}
	
	@Override
	public boolean isEmpty() 
	{
		for(ItemStack itemstack : this.items) 	
		{
			if(!itemstack.isEmpty()) 
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getItem(int i) 
	{
		return this.items.get(i);
	}
	
	@Override
	public ItemStack removeItem(int i1, int i2) 
	{
		return ItemStackHelper.removeItem(this.items, i1, i2);
	}
	
	@Override
	public ItemStack removeItemNoUpdate(int i) 
	{
		return ItemStackHelper.takeItem(this.items, i);
	}
	
	@Override
	public void setItem(int i, ItemStack stack) 
	{
		ItemStack itemstack = this.items.get(i);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(i, stack);
		if(stack.getCount() > this.getMaxStackSize()) 
		{
			stack.setCount(this.getMaxStackSize());
		}
		
		if (i == 0 && !flag) 
		{
			this.cleaningTotalTime = 3500;
			this.cleaningProgress = 0;
			this.setChanged();
		}
	}
	
	@Override
	public boolean stillValid(PlayerEntity player) 
	{
		if(this.level.getBlockEntity(this.worldPosition) != this) 
		{
			return false;
		} 
		else 
		{
			return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}
	
	@Override
	public void clearContent() 
	{
		this.items.clear();
	}
	
	public void setRecipeUsed(@Nullable IRecipe<?> recipe) 
	{
		if(recipe != null) 
		{
			ResourceLocation resourcelocation = recipe.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}
	
	public static boolean isFuel(ItemStack stack) 
	{
		return getFuel().containsKey(stack.getItem());
	}

	public void fillStackedContents(RecipeItemHelper helper) 
	{
		for(ItemStack itemstack : this.items) 
		{
			helper.accountStack(itemstack);
		}
	}
	
	@Override
	public boolean canPlaceItem(int i, ItemStack stack) 
	{
		if(i == 2) 
		{
			return false;
		} 
		else if(i != 1) 
		{
			return true;
		} 
		else 
		{
			return isFuel(stack);
		}
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) 
	{
		return new FossilCleanerContainer(windowId, playerInv, this, this);
	}

	@Override
	public ITextComponent getName() 
	{
		return ModUtils.tTC("container", "fossil_cleaner");
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		return this.getName();
	}
	
	@Override
	@Nullable
	public ITextComponent getCustomName() 
	{
		return this.name;
	}

	public void setCustomName(ITextComponent text) 
	{
		this.name = text;
	}
}
