package lostworlds.server.container.slot;

import lostworlds.server.block.entity.FossilCleanerTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FossilCleanerFuelSlot extends Slot {
	public FossilCleanerFuelSlot(IInventory inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return FossilCleanerTileEntity.isFuel(stack);
	}
}
