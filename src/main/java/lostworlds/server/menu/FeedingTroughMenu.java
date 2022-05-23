package lostworlds.server.menu;

import lostworlds.server.block.entity.FeedingTroughBlockEntity;
import lostworlds.server.menu.slot.FeedingTroughSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FeedingTroughMenu extends AbstractContainerMenu {
	public FeedingTroughMenu(MenuType<? extends FeedingTroughMenu> type, int windowId, Inventory inventory, FeedingTroughBlockEntity blockEntity) {
		super(type, windowId);

		this.addSlot(new FeedingTroughSlot(blockEntity, 0, 80, 20));

		for (int l = 0; l < 3; ++l) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(inventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 109));
		}
	}

	public FeedingTroughMenu(MenuType<? extends FeedingTroughMenu> type, int windowId, Inventory inventory, FriendlyByteBuf buffer) {
		this(type, windowId, inventory, (FeedingTroughBlockEntity) inventory.player.level.getBlockEntity(buffer.readBlockPos()));
	}

	@Override
	public boolean stillValid(Player player) {
		return !player.isSpectator();
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(containerSlot);

		if (slot != null && slot.hasItem()) {
			ItemStack current = slot.getItem();
			previous = current.copy();

			if (containerSlot < 1) {
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
