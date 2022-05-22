package lostworlds.server.container.slot;

import lostworlds.server.block.entity.FossilCleanerTileEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FossilCleanerFuelSlot extends Slot {
	public FossilCleanerFuelSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return FossilCleanerTileEntity.isFuel(stack);
	}
}
