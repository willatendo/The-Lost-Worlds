package lostworlds.server.menu;

import java.util.Optional;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.menu.inventory.PaleontologyTableInventory;
import lostworlds.server.menu.inventory.PaleontologyTableResultInventory;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import lostworlds.server.menu.recipes.PaleontologyTableRecipe;
import lostworlds.server.menu.slot.PaleontologyTableResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

public class PaleontologyTableMenu extends AbstractContainerMenu {
	private final PaleontologyTableInventory craftSlots = new PaleontologyTableInventory(this, 3, 3);
	private final PaleontologyTableResultInventory resultSlots = new PaleontologyTableResultInventory();
	private final ContainerLevelAccess containerLevelAccess;
	private final Player player;

	public PaleontologyTableMenu(MenuType<? extends PaleontologyTableMenu> type, int windowID, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
		super(type, windowID);
		this.containerLevelAccess = containerLevelAccess;
		this.player = inventory.player;
		this.addSlot(new PaleontologyTableResultSlot(inventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
		}
	}

	public PaleontologyTableMenu(MenuType<? extends PaleontologyTableMenu> type, int windowId, Inventory inventory, FriendlyByteBuf buffer) {
		this(type, windowId, inventory, ContainerLevelAccess.NULL);
	}

	protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, Level world, Player player, PaleontologyTableInventory inv, PaleontologyTableResultInventory result) {
		if (!world.isClientSide) {
			ServerPlayer serverPlayer = (ServerPlayer) player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<PaleontologyTableRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(LostWorldsRecipeTypes.PALEONTOLOGY_TABLE_RECIPE, inv, world);
			if (optional.isPresent()) {
				PaleontologyTableRecipe recipe = optional.get();
				if (result.setRecipeUsed(world, serverPlayer, recipe)) {
					itemstack = recipe.assemble(inv);
				}
			}

			result.setItem(0, itemstack);
			serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, itemstack));
		}
	}

	@Override
	public void slotsChanged(Container container) {
		this.containerLevelAccess.execute((level, pos) -> {
			slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots);
		});
	}

	public void fillCraftSlotsStackedContents(StackedContents helper) {
		this.craftSlots.fillStackedContents(helper);
	}

	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}

	public boolean recipeMatches(Recipe<? super PaleontologyTableInventory> iRecipe) {
		return iRecipe.matches(this.craftSlots, this.player.level);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.containerLevelAccess.execute((level, pos) -> {
			this.clearContainer(player, this.craftSlots);
		});
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.containerLevelAccess, player, LostWorldsBlocks.PALEONTOLOGY_TABLE.get());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(containerSlot);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (containerSlot == 0) {
				this.containerLevelAccess.execute((world, pos) -> {
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

			slot.onTake(player, itemstack1);
			if (containerSlot == 0) {
				player.drop(itemstack1, false);
			}
		}

		return itemstack;
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}
}
