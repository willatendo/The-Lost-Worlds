package lostworlds.library.slot;

import lostworlds.library.item.DNAItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class DNASlot extends Slot
{
	public DNASlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return isDNA(stack);
	}
	
	public boolean isDNA(ItemStack stack)
	{
		if(stack.getItem() instanceof DNAItem)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
