package lostworlds.library.tileentity;

import java.util.Random;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.DNAInjectorBlock;
import lostworlds.library.container.DNAInjectorContainer;
import lostworlds.library.recipe.DNAInjectorRecipe;
import lostworlds.library.util.ModUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class DNAInjectorTileEntity extends TileEntity implements IInventory, INamedContainerProvider, INameable, ITickableTileEntity	
{
	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	
	private int onTime;
	private int onDuration;
	private int injectingProgress;
	private int injectingTotalTime = 60;
	
	protected final IIntArray injectorData = new IIntArray()
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
					return injectingProgress;
				case 3:
					return injectingTotalTime;
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
					injectingProgress = value;
					break;
				case 3:
					injectingTotalTime = value;
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
	protected final IRecipeType<DNAInjectorRecipe> recipeType = RecipeInit.DNA_INJECTOR_RECIPE;
	
	@SuppressWarnings("unused")
	private ITextComponent name;

	public DNAInjectorTileEntity() 
	{
		super(TileEntityInit.DNA_INJECTOR_TILE_ENTITY.get());
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.injectingProgress = nbt.getInt("InjectTime");
		this.injectingTotalTime = nbt.getInt("InjectTimeTotal");
		this.onDuration = this.getInjectDuration();if(nbt.contains("CustomName", 8)) 
		{
			this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("InjectTime", this.injectingProgress);
		nbt.putInt("InjectTimeTotal", this.injectingTotalTime);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}
	
	public IIntArray getInjectorData()
	{
		return this.injectorData;
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
				if(this.isOn() || !this.items.get(0).isEmpty()) 
				{
					IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<DNAInjectorRecipe>)this.recipeType, this, this.level).orElse(null);
					if(!this.isOn() && this.canInjectWith(irecipe)) 
					{
						this.onTime = this.getInjectDuration();
						this.onDuration = this.onTime;
						if(this.isOn()) 
						{
							flag1 = true;
						}
					}
					
					if(this.isOn() && this.canInjectWith(irecipe)) 
					{
						++this.injectingProgress;
						if(this.injectingProgress == this.injectingTotalTime) 
						{
							this.injectingProgress = 0;
							this.injectingTotalTime = this.getTotalInjectTime();
							this.canInject(irecipe);
							flag1 = true;
						}
					} 
					else 
					{
						this.injectingProgress = 0;
					}
				} 
				else if(!this.isOn() && this.injectingProgress > 0) 
				{
					this.injectingProgress = MathHelper.clamp(this.injectingProgress - 2, 0, this.injectingTotalTime);
				}
				
				if(flag != this.isOn()) 
				{
					flag1 = true;
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(DNAInjectorBlock.ON, Boolean.valueOf(this.isOn())), 3);
				}
			}
		}
		
		if(flag1) 
		{
			this.setChanged();
		}
	}
	
	protected boolean canInjectWith(@Nullable IRecipe<?> recipe) 
	{
		if(!this.items.get(0).isEmpty() && !this.items.get(1).isEmpty() && recipe != null) 
		{
			ItemStack itemstack = recipe.getResultItem();
			if(itemstack.isEmpty()) 
			{
				return false;
			} 
			else 
			{
				ItemStack itemstack1 = this.items.get(2);
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
	
	private void canInject(@Nullable IRecipe<?> recipe) 
	{
		if(recipe != null && this.canInjectWith(recipe)) 
		{
			ItemStack dnaDisc = this.items.get(0);
			ItemStack egg = this.items.get(1);
			ItemStack itemstack1 = recipe.getResultItem();
			ItemStack itemstack2 = this.items.get(2);
			if(itemstack2.isEmpty()) 
			{
				this.items.set(2, itemstack1.copy());
			} 
			else if(itemstack2.getItem() == itemstack1.getItem()) 
			{
				itemstack2.grow(itemstack1.getCount());
			}
			
			if(!this.level.isClientSide) 
			{
				this.setRecipeUsed(recipe);
			}
			
			Random rand = new Random();
			
			if(dnaDisc.getMaxDamage() - dnaDisc.getDamageValue() <= 1)
			{
				dnaDisc.shrink(1);
			}
			else
			{
				dnaDisc.hurt(1, rand, null);
			}
			
			egg.shrink(1);
		}
	}
	
	protected int getTotalInjectTime() 
	{
		return this.level.getRecipeManager().getRecipeFor((IRecipeType<DNAInjectorRecipe>)this.recipeType, this, this.level).map(DNAInjectorRecipe::getInjectingTime).orElse(50);
	}
	
	protected int getInjectDuration() 
	{
		return 60;
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
			this.injectingTotalTime = this.getTotalInjectTime();
			this.injectingProgress = 0;
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
	public boolean canPlaceItem(int i, ItemStack stack) 
	{
		if(i == 2) 
		{
			return false;
		} 
		else
		{
			return true;
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
	
	@Nullable
	public IRecipe<?> getRecipeUsed() 
	{
		return null;
	}

	public void fillStackedContents(RecipeItemHelper helper) 
	{
		for(ItemStack itemstack : this.items) 
		{
			helper.accountStack(itemstack);
		}
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) 
	{
		return new DNAInjectorContainer(windowId, playerInv, this, this);
	}

	@Override
	public ITextComponent getName() 
	{
		return ModUtil.tTC("container", "dna_extractor");
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		return this.getName();
	}
	
	public void setCustomName(ITextComponent text) 
	{
		this.name = text;
	}
}