package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.entity.FeedingTroughTileEntity;
import lostworlds.server.container.slot.FeedingTroughSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FeedingTroughContainer extends AbstractContainerMenu {
	public FeedingTroughContainer(MenuType<? extends FeedingTroughContainer> containerType, int windowId, Inventory playerInventory, Container inventory) {
		super(containerType, windowId);

		this.addSlot(new FeedingTroughSlot(inventory, 0, 80, 20));

		for (int l = 0; l < 3; ++l) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
		}
	}

	public FeedingTroughContainer(MenuType<? extends FeedingTroughContainer> containerType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(containerType, windowId, playerInventory, getTileEntity(playerInventory, buffer));
	}

	private static FeedingTroughTileEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "Error: " + FeedingTroughContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + FeedingTroughContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final BlockEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof FeedingTroughTileEntity) {
			return (FeedingTroughTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + FeedingTroughContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(Player entity) {
		return !entity.isSpectator();
	}

	@Override
	public ItemStack quickMoveStack(Player player, int fromSlot) {
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
