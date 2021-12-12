package lostworlds.library.container.slot;

import lostworlds.library.block.utils.Foods;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FeedingTroughSlot extends Slot
{
	public FeedingTroughSlot(IInventory inventory, int index, int xPosition, int yPosition) 
	{
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{	
		return Foods.HERBIVORE_FOODS.contains(stack.getItem()) || Foods.CARNIVORE_FOODS.contains(stack.getItem()) || Foods.PISCAVORE_FOODS.contains(stack.getItem()) || Foods.INSECTIVORE_FOODS.contains(stack.getItem());
	}
}
