package lostworlds.server.menu.slot;

import lostworlds.server.block.utils.Foods;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FeedingTroughSlot extends Slot {
	public FeedingTroughSlot(Container inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return Foods.getHerbivoreFoods().contains(stack.getItem()) || Foods.getCarnivoreFoods().contains(stack.getItem()) || Foods.getPiscavoreFoods().contains(stack.getItem()) || Foods.getInsectivoreFoods().contains(stack.getItem());
	}
}
