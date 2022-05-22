package lostworlds.server.container;

import java.util.Optional;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.container.inventory.ArchaeologyTableInventory;
import lostworlds.server.container.inventory.ArchaeologyTableResultInventory;
import lostworlds.server.container.recipes.ArchaeologyTableRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.slot.ArchaeologyTableResultSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;

public class ArchaeologyTableContainer extends AbstractContainerMenu {
	private final ArchaeologyTableInventory craftSlots = new ArchaeologyTableInventory(this, 3, 3);
	private final ArchaeologyTableResultInventory resultSlots = new ArchaeologyTableResultInventory();
	private final ContainerLevelAccess posCallable;
	private final Player player;

	public ArchaeologyTableContainer(MenuType<? extends ArchaeologyTableContainer> containerType, int windowID, Inventory playerInventory, ContainerLevelAccess posCallable) {
		super(containerType, windowID);
		this.posCallable = posCallable;
		this.player = playerInventory.player;
		this.addSlot(new ArchaeologyTableResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
		}
	}

	public ArchaeologyTableContainer(MenuType<? extends ArchaeologyTableContainer> containerType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(containerType, windowId, playerInventory, ContainerLevelAccess.NULL);
	}

	protected static void slotChangedCraftingGrid(int slot, Level world, Player player, ArchaeologyTableInventory inv, ArchaeologyTableResultInventory result) {
		if (!world.isClientSide) {
			ServerPlayer serverplayerentity = (ServerPlayer) player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<ArchaeologyTableRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(LostWorldsRecipes.ARCHAEOLOGY_TABLE_RECIPE, inv, world);
			if (optional.isPresent()) {
				ArchaeologyTableRecipe recipe = optional.get();
				if (result.setRecipeUsed(world, serverplayerentity, recipe)) {
					itemstack = recipe.assemble(inv);
				}
			}

			result.setItem(0, itemstack);
			serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(slot, 0, itemstack));
		}
	}

	@Override
	public void slotsChanged(Container iinv) {
		this.posCallable.execute((world, pos) -> {
			slotChangedCraftingGrid(this.containerId, world, this.player, this.craftSlots, this.resultSlots);
		});
	}

	public void fillCraftSlotsStackedContents(StackedContents helper) {
		this.craftSlots.fillStackedContents(helper);
	}

	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}

	public boolean recipeMatches(Recipe<? super ArchaeologyTableInventory> iRecipe) {
		return iRecipe.matches(this.craftSlots, this.player.level);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.posCallable.execute((world, pos) -> {
			this.clearContainer(player, world, this.craftSlots);
		});
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.posCallable, player, LostWorldsBlocks.ARCHAEOLOGY_TABLE.get());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(containerSlot);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (containerSlot == 0) {
				this.posCallable.execute((world, pos) -> {
					itemstack1.getItem().onCraftedBy(itemstack1, world, player);
				});
				if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (containerSlot >= 10 && containerSlot < 46) {
				if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
					if (containerSlot < 37) {
						if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(player, itemstack1);
			if (containerSlot == 0) {
				player.drop(itemstack2, false);
			}
		}

		return itemstack;
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}
}
