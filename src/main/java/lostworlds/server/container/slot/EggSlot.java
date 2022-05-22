package lostworlds.server.container.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EggSlot extends Slot {
	public EggSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() == Items.EGG;
	}
}
