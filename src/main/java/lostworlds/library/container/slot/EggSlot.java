package lostworlds.library.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EggSlot extends Slot {
	public EggSlot(IInventory inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() == Items.EGG;
	}
}
