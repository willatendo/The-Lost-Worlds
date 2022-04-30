package lostworlds.server.container.slot;

import lostworlds.server.block.utils.Foods;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FeedingTroughSlot extends Slot {
	public FeedingTroughSlot(IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return Foods.getHerbivoreFoods().contains(stack.getItem()) || Foods.getCarnivoreFoods().contains(stack.getItem()) || Foods.getPiscavoreFoods().contains(stack.getItem()) || Foods.getInsectivoreFoods().contains(stack.getItem());
	}
}
