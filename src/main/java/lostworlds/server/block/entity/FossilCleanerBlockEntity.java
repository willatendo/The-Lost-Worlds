package lostworlds.server.block.entity;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.AnalyzerBlock;
import lostworlds.server.menu.FossilCleanerMenu;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.FossilCleanerRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class FossilCleanerBlockEntity extends BlockEntity implements Container, MenuProvider, Nameable, WorldlyContainer {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2, 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int cleaningProgress;
	private int cleaningTotalTime = 1000;

	protected final ContainerData cleanerData = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
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
		public void set(int index, int value) {
			switch (index) {
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
		public int getCount() {
			return 4;
		}
	};

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final RecipeType<FossilCleanerRecipe> recipeType = LostWorldsRecipes.FOSSIL_CLEANER_RECIPE;

	private Component name;

	public FossilCleanerBlockEntity(BlockEntityType<FossilCleanerBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public static Map<Item, Integer> getFuel() {
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		add(map, Items.WATER_BUCKET, 3500);
		add(map, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem(), 100);
		return map;
	}

	private static void add(Map<Item, Integer> map, ItemLike itemProvider, int length) {
		Item item = itemProvider.asItem();
		map.put(item, length);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items);
		this.onTime = tag.getInt("OnTime");
		this.cleaningProgress = tag.getInt("CleanTime");
		this.cleaningTotalTime = tag.getInt("CleanTimeTotal");
		this.onDuration = this.getCleanDuration(this.items.get(1));
		if (tag.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("OnTime", this.onTime);
		tag.putInt("CleanTime", this.cleaningProgress);
		tag.putInt("CleanTimeTotal", this.cleaningTotalTime);
		ContainerHelper.saveAllItems(tag, this.items);
	}

	public ContainerData getCleanerData() {
		return this.cleanerData;
	}

	public boolean isOn() {
		return this.onTime > 0;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, FossilCleanerBlockEntity blockEntity) {
		boolean flag = blockEntity.isOn();
		boolean flag1 = false;
		if (blockEntity.isOn()) {
			--blockEntity.onTime;
		}

		if (!blockEntity.level.isClientSide) {
			if (blockEntity.level.hasNeighborSignal(blockEntity.getBlockPos())) {
				ItemStack fuel = blockEntity.items.get(1);
				if (blockEntity.isOn() || !fuel.isEmpty() && !blockEntity.items.get(0).isEmpty()) {
					Recipe<?> irecipe = blockEntity.level.getRecipeManager().getRecipeFor((RecipeType<FossilCleanerRecipe>) blockEntity.recipeType, blockEntity, blockEntity.level).orElse(null);
					if (!blockEntity.isOn() && blockEntity.canClean(irecipe)) {
						blockEntity.onTime = blockEntity.getCleanDuration(fuel);
						blockEntity.onDuration = blockEntity.onTime;
						if (blockEntity.isOn()) {
							flag1 = true;
							if (fuel.hasContainerItem()) {
								blockEntity.items.set(1, fuel.getContainerItem());
							} else if (!fuel.isEmpty()) {
								fuel.shrink(1);
								if (fuel.isEmpty()) {
									blockEntity.items.set(1, fuel.getContainerItem());
								}
							}
						}
					}

					if (blockEntity.isOn() && blockEntity.canClean(irecipe)) {
						++blockEntity.cleaningProgress;
						if (blockEntity.cleaningProgress == blockEntity.cleaningTotalTime) {
							blockEntity.cleaningProgress = 0;
							blockEntity.cleaningTotalTime = blockEntity.getCleanDuration(fuel);
							blockEntity.clean(irecipe);
							flag1 = true;
						}
					} else {
						blockEntity.cleaningProgress = 0;
					}
				} else if (!blockEntity.isOn() && blockEntity.cleaningProgress > 0) {
					blockEntity.cleaningProgress = Mth.clamp(blockEntity.cleaningProgress - 2, 0, blockEntity.cleaningTotalTime);
				}

				if (flag != blockEntity.isOn()) {
					flag1 = true;
					blockEntity.level.setBlock(blockEntity.worldPosition, blockEntity.level.getBlockState(blockEntity.worldPosition).setValue(AnalyzerBlock.ON, Boolean.valueOf(blockEntity.isOn())), 3);
				}
			}
		}

		if (flag1) {
			blockEntity.setChanged();
		}
	}

	private boolean canClean(@Nullable Recipe<?> recipe) {
		if (!this.items.get(0).isEmpty() && recipe != null) {
			ItemStack result = recipe.getResultItem();
			if (result.isEmpty()) {
				return false;
			} else {
				ItemStack output = this.items.get(2);
				if (output.isEmpty()) {
					return true;
				} else if (!output.sameItem(result)) {
					return false;
				} else if (output.getCount() + result.getCount() <= this.getMaxStackSize() && output.getCount() + result.getCount() <= output.getMaxStackSize()) {
					return true;
				} else {
					return output.getCount() + result.getCount() <= result.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private void clean(@Nullable Recipe<?> recipe) {
		if (recipe != null && this.canClean(recipe)) {
			ItemStack input = this.items.get(0);
			ItemStack result = recipe.getResultItem();
			ItemStack output = this.items.get(2);
			if (output.isEmpty()) {
				this.items.set(2, result.copy());
			} else if (output.getItem() == result.getItem()) {
				output.grow(result.getCount());
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(recipe);
			}

			input.shrink(1);
		}
	}

	protected int getCleanDuration(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			Item item = stack.getItem();
			return getFuel().getOrDefault(item, 0);
		}
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
	public ItemStack getItem(int i) {
		return this.items.get(i);
	}

	@Override
	public ItemStack removeItem(int i1, int i2) {
		return ContainerHelper.removeItem(this.items, i1, i2);
	}

	@Override
	public ItemStack removeItemNoUpdate(int i) {
		return ContainerHelper.takeItem(this.items, i);
	}

	@Override
	public void setItem(int i, ItemStack stack) {
		ItemStack itemstack = this.items.get(i);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(i, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}

		if (i == 0 && !flag) {
			this.cleaningTotalTime = 3500;
			this.cleaningProgress = 0;
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
	public void clearContent() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable Recipe<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	public static boolean isFuel(ItemStack stack) {
		return getFuel().containsKey(stack.getItem());
	}

	public void fillStackedContents(StackedContents helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}
	}

	@Override
	public boolean canPlaceItem(int i, ItemStack stack) {
		if (i == 2) {
			return false;
		} else if (i != 1) {
			return true;
		} else {
			return isFuel(stack);
		}
	}

	@Override
	public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player player) {
		return new FossilCleanerMenu(LostWorldsMenus.FOSSIL_CLEANER_CONTAINER.get(), windowID, playerInventory, this);
	}

	@Override
	public Component getName() {
		return LostWorldsUtils.tTC("container", "fossil_cleaner");
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
		if (direction == Direction.DOWN && slot == 1) {
			Item item = stack.getItem();
			if (item != Items.BUCKET || item != Items.GLASS_BOTTLE) {
				return false;
			}
		}

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
