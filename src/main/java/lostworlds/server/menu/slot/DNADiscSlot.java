package lostworlds.server.menu.slot;

import lostworlds.server.LostWorldsTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DNADiscSlot extends Slot {
	public DNADiscSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.is(LostWorldsTags.ModItemTags.DNA_DISCS.tag);
	}
}
