package lostworlds.server.block.entity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.FossilGrinderBlock;
import lostworlds.server.container.FossilGrinderContainer;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.container.recipes.FossilGrinderRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.item.ModBoneMealItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.Nameable;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class FossilGrinderTileEntity extends BlockEntity implements Container, MenuProvider, Nameable, TickableBlockEntity, WorldlyContainer {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2, 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 0 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int grindingProgress;
	private int grindingTotalTime;

	protected final ContainerData grinderData = new ContainerData() {
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
	protected final RecipeType<FossilGrinderRecipe> recipeType = LostWorldsRecipes.FOSSIL_GRINDER_RECIPE;

	private Component name;

	public FossilGrinderTileEntity() {
		super(LostWorldsBlockEntities.FOSSIL_GRINDER_TILE_ENTITY.get());
	}

	public int getGrindingProgress() {
		return grindingProgress;
	}

	public int getGrindingTotalTime() {
		return this.grindingTotalTime;
	}

	@Override
	public void load(BlockState state, CompoundTag nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.grindingProgress = nbt.getInt("GrindTime");
		this.grindingTotalTime = nbt.getInt("GrindTimeTotal");
		this.onDuration = this.getGrindDuration();
		if (nbt.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("GrindTime", this.grindingProgress);
		nbt.putInt("GrindTimeTotal", this.grindingTotalTime);
		ContainerHelper.saveAllItems(nbt, this.items);
		return nbt;
	}

	public ContainerData getGrinderData() {
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
					Recipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((RecipeType<FossilGrinderRecipe>) this.recipeType, this, this.level).orElse(null);
					if (!this.isOn() && this.canGrind(irecipe)) {
						this.onTime = this.getGrindDuration();
						this.onDuration = this.onTime;
						if (this.isOn()) {
							flag1 = true;
						}
					}

					if (this.isOn() && this.canGrind(irecipe)) {
						++this.grindingProgress;
						if (this.grindingProgress == this.grindingTotalTime) {
							this.grindingProgress = 0;
							this.grindingTotalTime = this.getTotalGrindTime();
							this.grind(irecipe);
							flag1 = true;
						}
					} else {
						this.grindingProgress = 0;
					}
				} else if (!this.isOn() && this.grindingProgress > 0) {
					this.grindingProgress = Mth.clamp(this.grindingProgress - 2, 0, this.grindingTotalTime);
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

	protected boolean canGrind(@Nullable Recipe<?> recipe) {
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

	private void grind(@Nullable Recipe<?> recipe) {
		if (recipe != null && this.canGrind(recipe)) {
			ItemStack fossil = this.items.get(0);
			ItemStack result = recipe.getResultItem();
			ItemStack output = this.items.get(1);
			ItemStack waste = this.items.get(2);
			if (result.getItem() instanceof ModBoneMealItem) {
				if (waste.isEmpty()) {
					this.items.set(2, result.copy());
				} else if (waste.getItem() == result.getItem()) {
					waste.grow(result.getCount());
				}
			} else {
				if (output.isEmpty()) {
					this.items.set(1, result.copy());
				} else if (output.getItem() == result.getItem()) {
					output.grow(result.getCount());
				}
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(recipe);
			}

			fossil.shrink(1);
		}
	}

	protected int getTotalGrindTime() {
		return this.level.getRecipeManager().getRecipeFor((RecipeType<FossilGrinderRecipe>) this.recipeType, this, this.level).map(FossilGrinderRecipe::getGrindTime).orElse(300);
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
		return ContainerHelper.removeItem(this.items, slot1, slot2);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
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
	public boolean stillValid(Player player) {
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

	public void setRecipeUsed(@Nullable Recipe<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@Nullable
	public Recipe<?> getRecipeUsed() {
		return null;
	}

	public void fillStackedContents(StackedContents helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}
	}

	@Override
	public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player player) {
		return new FossilGrinderContainer(LostWorldsContainers.FOSSIL_GRINDER_CONTAINER.get(), windowID, playerInventory, this, this);
	}

	@Override
	public Component getName() {
		return LostWorldsUtils.tTC("container", "fossil_grinder");
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public Component getCustomName() {
		return this.name;
	}

	public void setCustomName(Component text) {
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
