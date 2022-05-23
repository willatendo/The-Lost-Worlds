package lostworlds.server.menu;

import lostworlds.server.block.entity.DisplayCaseBlockEntity;
import lostworlds.server.item.FossilItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DisplayCaseMenu extends AbstractContainerMenu {
	private ItemStackHandler handler;

	public DisplayCaseMenu(MenuType<? extends DisplayCaseMenu> type, int windowId, Inventory inventory, ItemStackHandler handler) {
		super(type, windowId);
		this.handler = handler;

		this.addSlot(new SlotItemHandler(handler, 0, 80, 20) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return correctTypeOfItem(stack);
			}

			public boolean correctTypeOfItem(ItemStack stack) {
				if (stack.getItem() instanceof BlockItem) {
					return false;
				} else if (stack.getItem() instanceof FossilItem) {
					return false;
				} else {
					return true;
				}
			}
		});

		for (int l = 0; l < 3; ++l) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(inventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 109));
		}
	}

	public DisplayCaseMenu(MenuType<? extends DisplayCaseMenu> type, int windowId, Inventory inventory, FriendlyByteBuf buffer) {
		this(type, windowId, inventory, ((DisplayCaseBlockEntity) Minecraft.getInstance().level.getBlockEntity(buffer.readBlockPos())).handler);
	}

	@Override
	public boolean stillValid(Player entity) {
		return !entity.isSpectator();
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(containerSlot);

		if (slot != null && slot.hasItem()) {
			ItemStack current = slot.getItem();
			previous = current.copy();

			if (containerSlot < this.handler.getSlots()) {
				if (!this.moveItemStackTo(current, this.handler.getSlots(), this.handler.getSlots() + 36, true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(current, 0, 6, false)) {
					return ItemStack.EMPTY;
				}
			}

			if (current.getCount() == 0) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (current.getCount() == previous.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(player, current);
		}
		return previous;
	}
}
