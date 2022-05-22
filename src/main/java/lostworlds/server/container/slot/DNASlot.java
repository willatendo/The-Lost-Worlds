package lostworlds.server.container.slot;

import lostworlds.server.LostWorldsTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DNASlot extends Slot {
	public DNASlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem().is(LostWorldsTags.ModItemTags.DNA.tag);
	}
}
