package lostworlds.server.menu.slot;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class VileSlot extends Slot {
	public VileSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() == LostWorldsItems.EMPTY_VILE.get();
	}
}
