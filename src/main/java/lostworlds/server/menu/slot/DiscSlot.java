package lostworlds.server.menu.slot;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DiscSlot extends Slot {
	public DiscSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.sameItem(LostWorldsItems.STORAGE_DISC.get().getDefaultInstance());
	}
}
