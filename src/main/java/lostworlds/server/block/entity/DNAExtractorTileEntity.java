package lostworlds.server.block.entity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.DNAExtractorBlock;
import lostworlds.server.container.DNAExtractorContainer;
import lostworlds.server.container.recipes.AmberDNAExtractorRecipe;
import lostworlds.server.container.recipes.DNAExtractorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
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

public class DNAExtractorTileEntity extends TileEntity implements IInventory, INamedContainerProvider, INameable, ITickableTileEntity, ISidedInventory {
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	private int onTime;
	private int onDuration;
	private int extractingProgress;
	private int extractingTotalTime = 60;

	protected final IIntArray extractingData = new IIntArray() {
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
	protected final IRecipeType<DNAExtractorRecipe> recipeType = LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	protected final IRecipeType<AmberDNAExtractorRecipe> secondaryRecipeType = LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;

	@SuppressWarnings("unused")
	private ITextComponent name;

	public DNAExtractorTileEntity() {
		super(LostWorldsBlockEntities.DNA_EXTRACTOR_TILE_ENTITY);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.onTime = nbt.getInt("OnTime");
		this.extractingProgress = nbt.getInt("ExtractTime");
		this.extractingTotalTime = nbt.getInt("ExtractTimeTotal");
		this.onDuration = this.getExtractDuration();
		if (nbt.contains("CustomName", 8)) {
			this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		nbt.putInt("OnTime", this.onTime);
		nbt.putInt("ExtractTime", this.extractingProgress);
		nbt.putInt("ExtractTimeTotal", this.extractingTotalTime);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}

	public IIntArray getExtractingData() {
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
					IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<DNAExtractorRecipe>) this.recipeType, this, this.level).orElse(null);
					IRecipe<?> isecondaryecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<AmberDNAExtractorRecipe>) this.secondaryRecipeType, this, this.level).orElse(null);
					if (!this.isOn() && this.canExtractWith(irecipe)) {
						this.onTime = this.getExtractDuration();
						this.onDuration = this.onTime;
						if (this.isOn()) {
							flag1 = true;
						}
					} else if (!this.isOn() && this.canExtractWith(isecondaryecipe)) {
						this.onTime = this.getExtractDuration();
						this.onDuration = this.onTime;
						if (this.isOn()) {
							flag1 = true;
						}
					}

					if (this.isOn() && this.canExtractWith(irecipe)) {
						++this.extractingProgress;
						if (this.extractingProgress == this.extractingTotalTime) {
							this.extractingProgress = 0;
							this.extractingTotalTime = this.getTotalExtractTime();
							this.canExtract(irecipe);
							flag1 = true;
						}
					} else if (this.isOn() && this.canExtractWith(isecondaryecipe)) {
						++this.extractingProgress;
						if (this.extractingProgress == this.extractingTotalTime) {
							this.extractingProgress = 0;
							this.extractingTotalTime = this.getTotalExtractTime();
							this.canExtract(isecondaryecipe);
							flag1 = true;
						}
					} else {
						this.extractingProgress = 0;
					}
				} else if (!this.isOn() && this.extractingProgress > 0) {
					this.extractingProgress = MathHelper.clamp(this.extractingProgress - 2, 0, this.extractingTotalTime);
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

	private ItemStack output = ItemStack.EMPTY;
	private boolean hasOutput = false;

	protected boolean canExtractWith(@Nullable IRecipe<?> recipe) {
		if (!this.items.get(0).isEmpty() && !this.items.get(1).isEmpty() && recipe != null) {
			if (recipe instanceof AmberDNAExtractorRecipe && !hasOutput) {
				AmberDNAExtractorRecipe amber = (AmberDNAExtractorRecipe) recipe;
				output = amber.generateRandom();
				hasOutput = true;
			} else if (!(recipe instanceof AmberDNAExtractorRecipe)) {
				output = recipe.getResultItem();
			}

			if (output.isEmpty()) {
				hasOutput = false;
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.sameItem(output)) {
					return false;
				} else if (itemstack1.getCount() + output.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + output.getCount() <= itemstack1.getMaxStackSize()) {
					return true;
				} else {
					return itemstack1.getCount() + output.getCount() <= output.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private void canExtract(@Nullable IRecipe<?> recipe) {
		if (recipe != null && this.canExtractWith(recipe)) {
			ItemStack input = this.items.get(0);
			ItemStack vile = this.items.get(1);
			ItemStack result = recipe instanceof AmberDNAExtractorRecipe ? output : recipe.getResultItem();
			ItemStack slot = this.items.get(2);
			if (slot.isEmpty()) {
				this.items.set(2, result.copy());
			} else if (slot.getItem() == result.getItem()) {
				slot.grow(result.getCount());
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(recipe);
			}

			input.shrink(1);
			vile.shrink(1);
			hasOutput = false;
		}
	}

	protected int getTotalExtractTime() {
		return this.level.getRecipeManager().getRecipeFor((IRecipeType<DNAExtractorRecipe>) this.recipeType, this, this.level).map(DNAExtractorRecipe::getExtractingTime).orElse(60);
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
		return ItemStackHelper.removeItem(this.items, i1, i2);
	}

	@Override
	public ItemStack removeItemNoUpdate(int i) {
		return ItemStackHelper.takeItem(this.items, i);
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
	public boolean stillValid(PlayerEntity player) {
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
		return new DNAExtractorContainer(windowId, playerInv, this);
	}

	@Override
	public ITextComponent getName() {
		return LostWorldsUtils.tTC("container", "dna_extractor");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
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