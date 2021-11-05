package lostworlds.library.block.entity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.CultivatorBlock;
import lostworlds.library.container.CultivatorContainer;
import lostworlds.library.container.recipes.CultivatorRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
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
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class CultivatorTileEntity extends TileEntity implements IInventory, INamedContainerProvider, INameable, ITickableTileEntity, ISidedInventory
{
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[]{ 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[]{ 0 };
	
	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	
	private int onTime;
	private int onDuration;
	private int cultivatingProgess;
	private int cultivatingTotalTime;
	
	protected final IIntArray cultivatorData = new IIntArray()
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
					return cultivatingProgess;
				case 3:
					return cultivatingTotalTime;
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
					cultivatingProgess = value;
					break;
				case 3:
					cultivatingTotalTime = value;
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
	protected final IRecipeType<CultivatorRecipe> recipeType = RecipeInit.CULTIVATOR_RECIPE;

	private ITextComponent name;

	public CultivatorTileEntity() 
	{
		super(TileEntityInit.CULTIVATOR_TILE_ENTITY);
	}
	
	public int getcultivatingProgess()
	{
		return cultivatingProgess;
	}
	
	public int getcultivatingTotalTime()
	{
		return this.cultivatingTotalTime;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.cultivatingProgess = nbt.getInt("CultivateTime");
		this.cultivatingTotalTime = nbt.getInt("CultivateTimeTotal");
		this.onDuration = this.getGrindDuration();
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
		nbt.putInt("CultivateTime", this.cultivatingProgess);
		nbt.putInt("CultivateTimeTotal", this.cultivatingTotalTime);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}
	
	public IIntArray getCultivatorData()
	{
		return this.cultivatorData;
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
					IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<CultivatorRecipe>)this.recipeType, this, this.level).orElse(null);
					if(!this.isOn() && this.canCultivateWith(irecipe)) 
					{
						this.onTime = this.getGrindDuration();
						this.onDuration = this.onTime;
						if(this.isOn()) 
						{
							flag1 = true;
						}
					}
					
					if(this.isOn() && this.canCultivateWith(irecipe)) 
					{
						++this.cultivatingProgess;
						if(this.cultivatingProgess == this.cultivatingTotalTime) 
						{
							this.cultivatingProgess = 0;
							this.cultivatingTotalTime = this.getTotalGrindTime();
							this.canCultivate(irecipe);
							flag1 = true;
						}
					} 
					else 
					{
						this.cultivatingProgess = 0;
					}
				} 
				else if(!this.isOn() && this.cultivatingProgess > 0) 
				{
					this.cultivatingProgess = MathHelper.clamp(this.cultivatingProgess - 2, 0, this.cultivatingTotalTime);
				}
				
				if(flag != this.isOn()) 
				{
					flag1 = true;
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(CultivatorBlock.ON, Boolean.valueOf(this.isOn())), 3);
				}
			}
		}
		
		if(flag1) 
		{
			this.setChanged();
		}
	}
	
	protected boolean canCultivateWith(@Nullable IRecipe<?> recipe) 
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
	
	private void canCultivate(@Nullable IRecipe<?> recipe) 
	{
		if(recipe != null && this.canCultivateWith(recipe)) 
		{
			ItemStack fossil = this.items.get(0);
			ItemStack itemstack1 = recipe.getResultItem();
			ItemStack itemstack2 = this.items.get(1);
			if(itemstack2.isEmpty()) 
			{
				this.items.set(1, itemstack1.copy());
			} 
			else if(itemstack2.getItem() == itemstack1.getItem()) 
			{
				itemstack2.grow(itemstack1.getCount());
			}
			
			if(!this.level.isClientSide) 
			{
				this.setRecipeUsed(recipe);
			}
			
			fossil.shrink(1);
		}
	}
	
	protected int getTotalGrindTime() 
	{
		return this.level.getRecipeManager().getRecipeFor((IRecipeType<CultivatorRecipe>)this.recipeType, this, this.level).map(CultivatorRecipe::getCultivatingTime).orElse(300);
	}
	
	protected int getGrindDuration() 
	{
		return 300;
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
			this.cultivatingTotalTime = this.getTotalGrindTime();
			this.cultivatingProgess = 0;
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
		if(i == 1) 
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
		return new CultivatorContainer(windowId, playerInv, this, this);
	}

	@Override
	public ITextComponent getName() 
	{
		return ModUtils.tTC("container", "cultivator");
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

	@Override
	public int[] getSlotsForFace(Direction direction) 
	{
		if(direction == Direction.DOWN) 
		{
			return SLOTS_FOR_DOWN;
		} 
		else 
		{
			return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction direction) 
	{
		return this.canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) 
	{	
		return true;
	}
	
	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) 
	{
		if(!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
		{
			if(facing == Direction.UP)
			{
				return handlers[0].cast();
			}
			else if(facing == Direction.DOWN)
			{
				return handlers[1].cast();
			}
			else
			{
				return handlers[2].cast();
			}
		}
		return super.getCapability(capability, facing);
	}
}
