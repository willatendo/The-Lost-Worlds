package lostworlds.library.slot;

import lostworlds.library.item.SoftTissueItem;
import lostworlds.library.item.VileItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class SoftTissueSlot extends Slot
{
	public SoftTissueSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return isSoftTissue(stack);
	}
	
	public boolean isSoftTissue(ItemStack stack)
	{
		if(stack.getItem() instanceof SoftTissueItem || stack.getItem() instanceof VileItem)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
