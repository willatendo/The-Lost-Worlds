package lostworlds.server.container;

import java.util.List;

import com.google.common.collect.Lists;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.recipes.TimeMachineRecipe;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TimeMachineContainer extends Container {
	private final IWorldPosCallable access;
	private final IntReferenceHolder selectedRecipeIndex = IntReferenceHolder.standalone();
	private final World level;
	private List<TimeMachineRecipe> recipes = Lists.newArrayList();
	private ItemStack input = ItemStack.EMPTY;
	private long lastSoundTime;
	final Slot bookSlot;
	final Slot powerSlot;
	final Slot resultSlot;
	private Runnable slotUpdateListener = () -> {
	};
	public final IInventory container = new Inventory(2) {
		public void setChanged() {
			super.setChanged();
			TimeMachineContainer.this.slotsChanged(this);
			TimeMachineContainer.this.slotUpdateListener.run();
		}
	};
	private final CraftResultInventory resultContainer = new CraftResultInventory();

	public TimeMachineContainer(ContainerType<? extends TimeMachineContainer> containerType, int windowId, PlayerInventory inv, PacketBuffer buffer) {
		this(containerType, windowId, inv, IWorldPosCallable.NULL);
	}

	public TimeMachineContainer(ContainerType<? extends TimeMachineContainer> containerType, int windowId, PlayerInventory inv, IWorldPosCallable worldPos) {
		super(containerType, windowId);

		this.access = worldPos;
		this.level = inv.player.level;
		this.bookSlot = this.addSlot(new Slot(this.container, 0, 12, 33));
		this.powerSlot = this.addSlot(new Slot(this.container, 1, 31, 33));

		this.resultSlot = this.addSlot(new Slot(this.resultContainer, 2, 143, 33) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack) {
				stack.onCraftedBy(player.level, player, stack.getCount());
				TimeMachineContainer.this.resultContainer.awardUsedRecipes(player);
				ItemStack book = TimeMachineContainer.this.bookSlot.remove(1);
				ItemStack power = TimeMachineContainer.this.powerSlot.remove(1);
				if (!book.isEmpty() && !power.isEmpty()) {
					TimeMachineContainer.this.setupResultSlot();
				}

				worldPos.execute((world, pos) -> {
					long l = world.getGameTime();
					if (TimeMachineContainer.this.lastSoundTime != l) {
						world.playSound((PlayerEntity) null, pos, SoundEvents.BEACON_ACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
						TimeMachineContainer.this.lastSoundTime = l;
					}
				});
				return super.onTake(player, stack);
			}
		});

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}

		this.addDataSlot(this.selectedRecipeIndex);
	}

	@OnlyIn(Dist.CLIENT)
	public int getSelectedRecipeIndex() {
		return this.selectedRecipeIndex.get();
	}

	@OnlyIn(Dist.CLIENT)
	public List<TimeMachineRecipe> getRecipes() {
		return this.recipes;
	}

	@OnlyIn(Dist.CLIENT)
	public int getNumRecipes() {
		return this.recipes.size();
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasInputItem() {
		return this.bookSlot.hasItem() && this.powerSlot.hasItem() && !this.recipes.isEmpty();
	}

	@Override
	public boolean stillValid(PlayerEntity entity) {
		return stillValid(this.access, entity, LostWorldsBlocks.TIME_MACHINE.get());
	}

	@Override
	public boolean clickMenuButton(PlayerEntity entity, int button) {
		if (this.isValidRecipeIndex(button)) {
			this.selectedRecipeIndex.set(button);
			this.setupResultSlot();
		}

		return true;
	}

	private boolean isValidRecipeIndex(int index) {
		return index >= 0 && index < this.recipes.size();
	}

	@Override
	public void slotsChanged(IInventory inv) {
		ItemStack book = this.bookSlot.getItem();
		ItemStack power = this.powerSlot.getItem();
		if (book.getItem() != this.input.getItem()) {
			this.input = book.copy();
			this.setupRecipeList(inv, book);
		}
		if (power.getItem() != this.input.getItem()) {
			this.input = power.copy();
			this.setupRecipeList(inv, power);
		}
	}

	private void setupRecipeList(IInventory inv, ItemStack stack) {
		this.recipes.clear();
		this.selectedRecipeIndex.set(-1);
		this.resultSlot.set(ItemStack.EMPTY);
		if (!inv.isEmpty()) {
			this.recipes = this.level.getRecipeManager().getRecipesFor(LostWorldsRecipes.TIME_MACHINE_RECIPE, inv, this.level);
		}
	}

	private void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
			TimeMachineRecipe TimeMachineRecipe = this.recipes.get(this.selectedRecipeIndex.get());
			this.resultContainer.setRecipeUsed(TimeMachineRecipe);
			this.resultSlot.set(TimeMachineRecipe.assemble(this.container));
		} else {
			this.resultSlot.set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	@Override
	public ContainerType<?> getType() {
		return LostWorldsContainers.TIME_MACHINE_CONTAINER.get();
	}

	@OnlyIn(Dist.CLIENT)
	public void registerUpdateListener(Runnable run) {
		this.slotUpdateListener = run;
	}

	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity entity, int slotNum) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotNum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			Item item = itemstack1.getItem();
			if (slotNum == 2) {
				item.onCraftedBy(itemstack1, entity.level, entity);
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (slotNum == 0) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}
			} else if (slotNum == 1) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}
			}

			else if (itemstack1.getItem() == LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()) {
				if (!this.moveItemStackTo(itemstack1, 1, 2, true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (this.slots.get(0).hasItem() || !this.slots.get(0).mayPlace(itemstack1)) {
					return ItemStack.EMPTY;
				}

				ItemStack itemstack2 = itemstack1.copy();
				itemstack2.setCount(1);
				itemstack1.shrink(1);
				this.slots.get(0).set(itemstack2);
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(entity, itemstack1);
		}

		return itemstack;
	}

	public void removed(PlayerEntity player) {
		super.removed(player);
		this.resultContainer.removeItemNoUpdate(1);
		this.access.execute((world, pos) -> {
			this.clearContainer(player, player.level, this.container);
		});
	}
}
