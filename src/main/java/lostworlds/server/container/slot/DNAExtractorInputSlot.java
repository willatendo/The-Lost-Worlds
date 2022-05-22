package lostworlds.server.container.slot;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.item.AmberItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DNAExtractorInputSlot extends Slot {
	public DNAExtractorInputSlot(Container inventory, int x, int z, int y) {
		super(inventory, x, z, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem().is(LostWorldsTags.ModItemTags.SOFT_TISSUE.tag) || stack.getItem() instanceof AmberItem || stack.getItem().is(LostWorldsTags.ModItemTags.BLOOD_VILES.tag);
	}
}
