package lostworlds.server.block.entity;

import java.util.Random;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.DNAInjectorBlock;
import lostworlds.server.menu.DNAInjectorMenu;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.DNAInjectorRecipe;
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

public class DNAInjectorBlockEntity extends BlockEntity implements Container, MenuProvider, Nameable, WorldlyContainer {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int injectingProgress;
	private int injectingTotalTime = 60;

	protected final ContainerData injectorData = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
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
		public void set(int index, int value) {
			switch (index) {
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
		public int getCount() {
			return 4;
		}
	};

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final RecipeType<DNAInjectorRecipe> recipeType = LostWorldsRecipes.DNA_INJECTOR_RECIPE;

	@SuppressWarnings("unused")
	private Component name;

	public DNAInjectorBlockEntity(BlockEntityType<DNAInjectorBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items);
		this.onTime = tag.getInt("OnTime");
		this.injectingProgress = tag.getInt("InjectTime");
		this.injectingTotalTime = tag.getInt("InjectTimeTotal");
		this.onDuration = this.getInjectDuration();
		if (tag.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("OnTime", this.onTime);
		tag.putInt("InjectTime", this.injectingProgress);
		tag.putInt("InjectTimeTotal", this.injectingTotalTime);
		ContainerHelper.saveAllItems(tag, this.items);
	}

	public ContainerData getInjectorData() {
		return this.injectorData;
	}

	public boolean isOn() {
		return this.onTime > 0;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, DNAInjectorBlockEntity blockEntity) {
		boolean flag = blockEntity.isOn();
		boolean flag1 = false;
		if (blockEntity.isOn()) {
			--blockEntity.onTime;
		}

		if (!blockEntity.level.isClientSide) {
			if (blockEntity.level.hasNeighborSignal(blockEntity.getBlockPos())) {
				if (blockEntity.isOn() || !blockEntity.items.get(0).isEmpty()) {
					Recipe<?> irecipe = blockEntity.level.getRecipeManager().getRecipeFor((RecipeType<DNAInjectorRecipe>) blockEntity.recipeType, blockEntity, blockEntity.level).orElse(null);
					if (!blockEntity.isOn() && blockEntity.canInject(irecipe)) {
						blockEntity.onTime = blockEntity.getInjectDuration();
						blockEntity.onDuration = blockEntity.onTime;
						if (blockEntity.isOn()) {
							flag1 = true;
						}
					}

					if (blockEntity.isOn() && blockEntity.canInject(irecipe)) {
						++blockEntity.injectingProgress;
						if (blockEntity.injectingProgress == blockEntity.injectingTotalTime) {
							blockEntity.injectingProgress = 0;
							blockEntity.injectingTotalTime = blockEntity.getTotalInjectTime();
							blockEntity.inject(irecipe);
							flag1 = true;
						}
					} else {
						blockEntity.injectingProgress = 0;
					}
				} else if (!blockEntity.isOn() && blockEntity.injectingProgress > 0) {
					blockEntity.injectingProgress = Mth.clamp(blockEntity.injectingProgress - 2, 0, blockEntity.injectingTotalTime);
				}

				if (flag != blockEntity.isOn()) {
					flag1 = true;
					blockEntity.level.setBlock(blockEntity.worldPosition, blockEntity.level.getBlockState(blockEntity.worldPosition).setValue(DNAInjectorBlock.ON, Boolean.valueOf(blockEntity.isOn())), 3);
				}
			}
		}

		if (flag1) {
			blockEntity.setChanged();
		}
	}

	protected boolean canInject(@Nullable Recipe<?> recipe) {
		if (!this.items.get(0).isEmpty() && !this.items.get(1).isEmpty() && recipe != null) {
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

	private void inject(@Nullable Recipe<?> recipe) {
		if (recipe != null && this.canInject(recipe)) {
			ItemStack dnaDisc = this.items.get(0);
			ItemStack egg = this.items.get(1);
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

			Random rand = new Random();

			if (dnaDisc.getMaxDamage() - dnaDisc.getDamageValue() <= 1) {
				dnaDisc.shrink(1);
			} else {
				dnaDisc.hurt(1, rand, null);
			}

			egg.shrink(1);
		}
	}

	protected int getTotalInjectTime() {
		return this.level.getRecipeManager().getRecipeFor((RecipeType<DNAInjectorRecipe>) this.recipeType, this, this.level).map(DNAInjectorRecipe::getInjectingTime).orElse(50);
	}

	protected int getInjectDuration() {
		return 60;
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
			this.injectingTotalTime = this.getTotalInjectTime();
			this.injectingProgress = 0;
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
	public boolean canPlaceItem(int i, ItemStack stack) {
		if (i == 2) {
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
		return new DNAInjectorMenu(LostWorldsMenus.DNA_INJECTOR_CONTAINER.get(), windowID, playerInventory, this);
	}

	@Override
	public Component getName() {
		return LostWorldsUtils.tTC("container", "dna_injector");
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
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