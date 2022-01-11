package lostworlds.library.container;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.container.slot.FeedingTroughSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class FeedingTroughContainer extends Container {
	public FeedingTroughContainer(ContainerType<?> container, int windowId) {
		super(container, windowId);
	}

	public FeedingTroughContainer(int windowId, PlayerInventory inv, IInventory inventory) {
		super(ContainerInit.FEEDING_TROUGH_CONTAINER, windowId);

		this.addSlot(new FeedingTroughSlot(inventory, 0, 80, 20));

		for (int l = 0; l < 3; ++l) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(inv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(inv, i1, 8 + i1 * 18, 109));
		}
	}

	public static FeedingTroughContainer create(int windowId, PlayerInventory inv, PacketBuffer buffer) {
		return new FeedingTroughContainer(windowId, inv, new Inventory(1));
	}

	@Override
	public boolean stillValid(PlayerEntity entity) {
		return !entity.isSpectator();
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(fromSlot);

		if (slot != null && slot.hasItem()) {
			ItemStack current = slot.getItem();
			previous = current.copy();

			if (fromSlot < 1) {
				if (!this.moveItemStackTo(current, 1, 1 + 36, true)) {
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
