package lostworlds.server.block.entity;

import java.util.Random;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.DNAExtractorBlock;
import lostworlds.server.container.DNAExtractorContainer;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.container.recipes.AmberDNAExtractorRecipe;
import lostworlds.server.container.recipes.DNAExtractorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.item.AmberItem;
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

public class DNAExtractorTileEntity extends BlockEntity implements Container, MenuProvider, Nameable, TickableBlockEntity, WorldlyContainer {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int extractingProgress;
	private int extractingTotalTime = 60;

	protected final ContainerData extractingData = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return onTime;
			case 1:
				return onDuration;
			case 2:
				return extractingProgress;
			case 3:
				return extractingTotalTime;
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
				extractingProgress = value;
				break;
			case 3:
				extractingTotalTime = value;
				break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final RecipeType<DNAExtractorRecipe> recipeType = LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	protected final RecipeType<AmberDNAExtractorRecipe> secondaryRecipeType = LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;

	@SuppressWarnings("unused")
	private Component name;

	public DNAExtractorTileEntity() {
		super(LostWorldsBlockEntities.DNA_EXTRACTOR_TILE_ENTITY.get());
	}

	@Override
	public void load(BlockState state, CompoundTag nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.extractingProgress = nbt.getInt("ExtractTime");
		this.extractingTotalTime = nbt.getInt("ExtractTimeTotal");
		this.onDuration = this.getExtractDuration();
		if (nbt.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("ExtractTime", this.extractingProgress);
		nbt.putInt("ExtractTimeTotal", this.extractingTotalTime);
		ContainerHelper.saveAllItems(nbt, this.items);
		return nbt;
	}

	public ContainerData getExtractingData() {
		return this.extractingData;
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
					if (this.items.get(0).getItem() instanceof AmberItem) {
						if (!this.isOn() && this.canExtractAmber()) {
							this.onTime = this.getExtractDuration();
							this.onDuration = this.onTime;
							if (this.isOn()) {
								flag1 = true;
							}
						}

						if (this.isOn() && this.canExtractAmber()) {
							++this.extractingProgress;
							if (this.extractingProgress == this.extractingTotalTime) {
								this.extractingProgress = 0;
								this.extractingTotalTime = this.getTotalExtractTime();
								this.extractAmber();
								flag1 = true;
							}
						} else {
							this.extractingProgress = 0;
						}
					} else {

						Recipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((RecipeType<DNAExtractorRecipe>) this.recipeType, this, this.level).orElse(null);
						if (!this.isOn() && this.canExtract(irecipe)) {
							this.onTime = this.getExtractDuration();
							this.onDuration = this.onTime;
							if (this.isOn()) {
								flag1 = true;
							}
						}

						if (this.isOn() && this.canExtract(irecipe)) {
							++this.extractingProgress;
							if (this.extractingProgress == this.extractingTotalTime) {
								this.extractingProgress = 0;
								this.extractingTotalTime = this.getTotalExtractTime();
								this.extract(irecipe);
								flag1 = true;
							}
						} else {
							this.extractingProgress = 0;
						}
					}
				} else if (!this.isOn() && this.extractingProgress > 0) {
					this.extractingProgress = Mth.clamp(this.extractingProgress - 2, 0, this.extractingTotalTime);
				}

				if (flag != this.isOn()) {
					flag1 = true;
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(DNAExtractorBlock.ON, Boolean.valueOf(this.isOn())), 3);
				}
			}
		}

		if (flag1) {
			this.setChanged();
		}
	}

	protected boolean canExtract(@Nullable Recipe<?> recipe) {
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

	private void extract(@Nullable Recipe<?> recipe) {
		if (recipe != null && this.canExtract(recipe)) {
			ItemStack input = this.items.get(0);
			ItemStack vile = this.items.get(1);
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
			vile.shrink(1);
		}
	}

	private ItemStack output = ItemStack.EMPTY;
	private boolean hasOutput = false;

	protected boolean canExtractAmber() {
		Random rand = new Random();
		if (!this.items.get(0).isEmpty() && !this.items.get(1).isEmpty()) {
			if (!this.hasOutput) {
				this.output = LostWorldsTags.ModItemTags.AMBER_RESULTS.tag.getValues().get(rand.nextInt(LostWorldsTags.ModItemTags.AMBER_RESULTS.tag.getValues().size())).getDefaultInstance();
				this.hasOutput = true;
			}

			if (this.output.isEmpty()) {
				this.hasOutput = false;
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.sameItem(this.output)) {
					return false;
				} else if (itemstack1.getCount() + this.output.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + this.output.getCount() <= itemstack1.getMaxStackSize()) {
					return true;
				} else {
					return itemstack1.getCount() + this.output.getCount() <= this.output.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private void extractAmber() {
		if (this.canExtractAmber()) {
			ItemStack input = this.items.get(0);
			ItemStack vile = this.items.get(1);
			ItemStack result = this.output;
			ItemStack slot = this.items.get(2);
			if (slot.isEmpty()) {
				this.items.set(2, result.copy());
			} else if (slot.getItem() == result.getItem()) {
				slot.grow(result.getCount());
			}

			input.shrink(1);
			vile.shrink(1);
			hasOutput = false;
		}
	}

	protected int getTotalExtractTime() {
		return this.level.getRecipeManager().getRecipeFor((RecipeType<DNAExtractorRecipe>) this.recipeType, this, this.level).map(DNAExtractorRecipe::getExtractingTime).orElse(60);
	}

	protected int getExtractDuration() {
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
			this.extractingTotalTime = this.getTotalExtractTime();
			this.extractingProgress = 0;
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
		return new DNAExtractorContainer(LostWorldsContainers.DNA_EXTRACTOR_CONTAINER.get(), windowID, playerInventory, this, this);
	}

	@Override
	public Component getName() {
		return LostWorldsUtils.tTC("container", "dna_extractor");
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