package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.entity.DisplayCaseTileEntity;
import lostworlds.server.item.FossilItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class DisplayCaseContainer extends Container {
	public DisplayCaseContainer(ContainerType<? extends DisplayCaseContainer> containerType, int windowId, PlayerInventory inv, IInventory inventory) {
		super(containerType, windowId);

		this.addSlot(new Slot(inventory, 0, 80, 20) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return this.notBlockOrFossil(stack);
			}

			public boolean notBlockOrFossil(ItemStack stack) {
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

	public DisplayCaseContainer(ContainerType<? extends DisplayCaseContainer> containerType, int windowId, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(containerType, windowId, playerInventory, getTileEntity(playerInventory, buffer));
	}

	private static DisplayCaseTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof DisplayCaseTileEntity) {
			return (DisplayCaseTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + DisplayCaseContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
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
				if (!this.moveItemStackTo(current, 1, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(current, 0, 1, false)) {
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
