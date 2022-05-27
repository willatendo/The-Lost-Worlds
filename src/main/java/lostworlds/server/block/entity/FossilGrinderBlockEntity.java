package lostworlds.server.block.entity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.FossilGrinderBlock;
import lostworlds.server.item.ModBoneMealItem;
import lostworlds.server.menu.FossilGrinderMenu;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.FossilGrinderRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class FossilGrinderBlockEntity extends BlockEntity implements Container, MenuProvider, Nameable, WorldlyContainer {
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
	protected final RecipeType<FossilGrinderRecipe> recipeType = LostWorldsRecipeTypes.FOSSIL_GRINDER_RECIPE;

	private Component name;

	public FossilGrinderBlockEntity(BlockEntityType<FossilGrinderBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public int getGrindingProgress() {
		return grindingProgress;
	}

	public int getGrindingTotalTime() {
		return this.grindingTotalTime;
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items);
		this.onTime = tag.getInt("OnTime");
		this.grindingProgress = tag.getInt("GrindTime");
		this.grindingTotalTime = tag.getInt("GrindTimeTotal");
		this.onDuration = this.getGrindDuration();
		if (tag.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("OnTime", this.onTime);
		tag.putInt("GrindTime", this.grindingProgress);
		tag.putInt("GrindTimeTotal", this.grindingTotalTime);
		ContainerHelper.saveAllItems(tag, this.items);
	}

	public ContainerData getGrinderData() {
		return this.grinderData;
	}

	public boolean isOn() {
		return this.onTime > 0;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, FossilGrinderBlockEntity blockEntity) {
		boolean flag = blockEntity.isOn();
		boolean flag1 = false;
		if (blockEntity.isOn()) {
			--blockEntity.onTime;
		}

		if (!blockEntity.level.isClientSide) {
			if (blockEntity.level.hasNeighborSignal(blockEntity.getBlockPos())) {
				if (blockEntity.isOn() || !blockEntity.items.get(0).isEmpty()) {
					Recipe<?> irecipe = blockEntity.level.getRecipeManager().getRecipeFor((RecipeType<FossilGrinderRecipe>) blockEntity.recipeType, blockEntity, blockEntity.level).orElse(null);
					if (!blockEntity.isOn() && blockEntity.canGrind(irecipe)) {
						blockEntity.onTime = blockEntity.getGrindDuration();
						blockEntity.onDuration = blockEntity.onTime;
						if (blockEntity.isOn()) {
							flag1 = true;
						}
					}

					if (blockEntity.isOn() && blockEntity.canGrind(irecipe)) {
						++blockEntity.grindingProgress;
						if (blockEntity.grindingProgress == blockEntity.grindingTotalTime) {
							blockEntity.grindingProgress = 0;
							blockEntity.grindingTotalTime = blockEntity.getTotalGrindTime();
							blockEntity.grind(irecipe);
							flag1 = true;
						}
					} else {
						blockEntity.grindingProgress = 0;
					}
				} else if (!blockEntity.isOn() && blockEntity.grindingProgress > 0) {
					blockEntity.grindingProgress = Mth.clamp(blockEntity.grindingProgress - 2, 0, blockEntity.grindingTotalTime);
				}

				if (flag != blockEntity.isOn()) {
					flag1 = true;
					blockEntity.level.setBlock(blockEntity.worldPosition, blockEntity.level.getBlockState(blockEntity.worldPosition).setValue(FossilGrinderBlock.ON, Boolean.valueOf(blockEntity.isOn())), 3);
				}
			}
		}

		if (flag1) {
			blockEntity.setChanged();
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
		return new FossilGrinderMenu(LostWorldsMenus.FOSSIL_GRINDER_CONTAINER.get(), windowID, playerInventory, this);
	}

	@Override
	public Component getName() {
		return LostWorldsUtils.tTC("menu", "fossil_grinder");
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
