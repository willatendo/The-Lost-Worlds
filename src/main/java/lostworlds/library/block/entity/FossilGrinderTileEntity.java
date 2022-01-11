package lostworlds.library.block.entity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.FossilGrinderBlock;
import lostworlds.library.container.FossilGrinderContainer;
import lostworlds.library.container.recipes.FossilGrinderRecipe;
import lostworlds.library.item.ModBoneMealItem;
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

public class FossilGrinderTileEntity extends TileEntity implements IInventory, INamedContainerProvider, INameable, ITickableTileEntity, ISidedInventory {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2, 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 0 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int grindingProgress;
	private int grindingTotalTime;

	protected final IIntArray grinderData = new IIntArray() {
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return onTime;
			case 1:
				return onDuration;
			case 2:
				return grindingProgress;
			case 3:
				return grindingTotalTime;
			default:
				return 0;
			}
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
			case 0:
				onTime = value;
				break;
			case 1:
				onDuration = value;
				break;
			case 2:
				grindingProgress = value;
				break;
			case 3:
				grindingTotalTime = value;
				break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final IRecipeType<FossilGrinderRecipe> recipeType = RecipeInit.FOSSIL_GRINDER_RECIPE;

	private ITextComponent name;

	public FossilGrinderTileEntity() {
		super(TileEntityInit.FOSSIL_GRINDER_TILE_ENTITY);
	}

	public int getGrindingProgress() {
		return grindingProgress;
	}

	public int getGrindingTotalTime() {
		return this.grindingTotalTime;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.grindingProgress = nbt.getInt("GrindTime");
		this.grindingTotalTime = nbt.getInt("GrindTimeTotal");
		this.onDuration = this.getGrindDuration();
		if (nbt.contains("CustomName", 8)) {
			this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("GrindTime", this.grindingProgress);
		nbt.putInt("GrindTimeTotal", this.grindingTotalTime);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}

	public IIntArray getGrinderData() {
		return this.grinderData;
	}

	public boolean isOn() {
		return this.onTime > 0;
	}

	@Override
	public void tick() {
		boolean flag = this.isOn();
		boolean flag1 = false;
		if (this.isOn()) {
			--this.onTime;
		}

		if (!this.level.isClientSide) {
			if (this.level.hasNeighborSignal(this.getBlockPos())) {
				if (this.isOn() || !this.items.get(0).isEmpty()) {
					IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<FossilGrinderRecipe>) this.recipeType, this, this.level).orElse(null);
					if (!this.isOn() && this.canGrindWith(irecipe)) {
						this.onTime = this.getGrindDuration();
						this.onDuration = this.onTime;
						if (this.isOn()) {
							flag1 = true;
						}
					}

					if (this.isOn() && this.canGrindWith(irecipe)) {
						++this.grindingProgress;
						if (this.grindingProgress == this.grindingTotalTime) {
							this.grindingProgress = 0;
							this.grindingTotalTime = this.getTotalGrindTime();
							this.canGrind(irecipe);
							flag1 = true;
						}
					} else {
						this.grindingProgress = 0;
					}
				} else if (!this.isOn() && this.grindingProgress > 0) {
					this.grindingProgress = MathHelper.clamp(this.grindingProgress - 2, 0, this.grindingTotalTime);
				}

				if (flag != this.isOn()) {
					flag1 = true;
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FossilGrinderBlock.ON, Boolean.valueOf(this.isOn())), 3);
				}
			}
		}

		if (flag1) {
			this.setChanged();
		}
	}

	protected boolean canGrindWith(@Nullable IRecipe<?> recipe) {
		if (!this.items.get(0).isEmpty() && recipe != null) {
			ItemStack result = recipe.getResultItem();
			if (result.isEmpty()) {
				return false;
			} else {
				ItemStack softtissueoutput = this.items.get(1);
				ItemStack bonemealoutput = this.items.get(2);
				if (result.getItem() instanceof ModBoneMealItem) {
					if (bonemealoutput.isEmpty()) {
						return true;
					} else if (!bonemealoutput.sameItem(result)) {
						return false;
					} else if (bonemealoutput.getCount() + result.getCount() <= this.getMaxStackSize() && bonemealoutput.getCount() + result.getCount() <= bonemealoutput.getMaxStackSize()) {
						return true;
					} else {
						return bonemealoutput.getCount() + result.getCount() <= result.getMaxStackSize();
					}
				} else {
					if (softtissueoutput.isEmpty()) {
						return true;
					} else if (!softtissueoutput.sameItem(result)) {
						return false;
					} else if (softtissueoutput.getCount() + result.getCount() <= this.getMaxStackSize() && softtissueoutput.getCount() + result.getCount() <= softtissueoutput.getMaxStackSize()) {
						return true;
					} else {
						return softtissueoutput.getCount() + result.getCount() <= result.getMaxStackSize();
					}
				}
			}
		} else {
			return false;
		}
	}

	private void canGrind(@Nullable IRecipe<?> recipe) {
		if (recipe != null && this.canGrindWith(recipe)) {
			ItemStack fossil = this.items.get(0);
			ItemStack result = recipe.getResultItem();
			ItemStack slot1 = this.items.get(1);
			ItemStack slot2 = this.items.get(2);
			if (result.getItem() instanceof ModBoneMealItem) {
				if (slot2.isEmpty()) {
					this.items.set(2, result.copy());
				} else if (slot2.getItem() == result.getItem()) {
					slot2.grow(result.getCount());
				}
			} else {
				if (slot1.isEmpty()) {
					this.items.set(1, result.copy());
				} else if (slot1.getItem() == result.getItem()) {
					slot1.grow(result.getCount());
				}
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(recipe);
			}

			fossil.shrink(1);
		}
	}

	protected int getTotalGrindTime() {
		return this.level.getRecipeManager().getRecipeFor((IRecipeType<FossilGrinderRecipe>) this.recipeType, this, this.level).map(FossilGrinderRecipe::getGrindTime).orElse(300);
	}

	protected int getGrindDuration() {
		return 300;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot1, int slot2) {
		return ItemStackHelper.removeItem(this.items, slot1, slot2);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ItemStackHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		ItemStack itemstack = this.items.get(slot);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(slot, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}

		if (slot == 0 && !flag) {
			this.grindingTotalTime = this.getTotalGrindTime();
			this.grindingProgress = 0;
			this.setChanged();
		}
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (slot == 1 || slot == 2) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	public void fillStackedContents(RecipeItemHelper helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) {
		return new FossilGrinderContainer(windowId, playerInv, this, this);
	}

	@Override
	public ITextComponent getName() {
		return ModUtils.tTC("container", "fossil_grinder");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public ITextComponent getCustomName() {
		return this.name;
	}

	public void setCustomName(ITextComponent text) {
		this.name = text;
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		if (direction == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return this.canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return true;
	}

	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP) {
				return handlers[0].cast();
			} else if (facing == Direction.DOWN) {
				return handlers[1].cast();
			} else {
				return handlers[2].cast();
			}
		}
		return super.getCapability(capability, facing);
	}
}
