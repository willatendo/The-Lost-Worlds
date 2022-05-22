package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.entity.DisplayCaseTileEntity;
import lostworlds.server.item.FossilItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DisplayCaseContainer extends AbstractContainerMenu {
	private ItemStackHandler handler;

	public DisplayCaseContainer(MenuType<? extends DisplayCaseContainer> containerType, int windowId, Inventory inv, Container inventory, ItemStackHandler handler) {
		super(containerType, windowId);
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
				this.addSlot(new Slot(inv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(inv, i1, 8 + i1 * 18, 109));
		}
	}

	public DisplayCaseContainer(MenuType<? extends DisplayCaseContainer> containerType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(containerType, windowId, playerInventory, getTileEntity(playerInventory, buffer), ((DisplayCaseTileEntity) Minecraft.getInstance().level.getBlockEntity(buffer.readBlockPos())).handler);
	}

	private static DisplayCaseTileEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final BlockEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof DisplayCaseTileEntity) {
			return (DisplayCaseTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + DisplayCaseContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
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

			if (fromSlot < this.handler.getSlots()) {
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
