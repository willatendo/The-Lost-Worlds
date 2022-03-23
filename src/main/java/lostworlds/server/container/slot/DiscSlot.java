package lostworlds.server.container.slot;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class DiscSlot extends Slot {
	public DiscSlot(IInventory inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.sameItem(LostWorldsItems.STORAGE_DISC.get().getDefaultInstance());
	}
}
