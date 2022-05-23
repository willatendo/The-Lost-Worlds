package lostworlds.server.menu.slot;

import lostworlds.server.block.entity.FossilCleanerBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FossilCleanerFuelSlot extends Slot {
	public FossilCleanerFuelSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return FossilCleanerBlockEntity.isFuel(stack);
	}
}
