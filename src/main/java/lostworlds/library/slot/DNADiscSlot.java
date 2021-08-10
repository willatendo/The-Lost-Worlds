package lostworlds.library.slot;

import lostworlds.library.item.DiscItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class DNADiscSlot extends Slot
{
	public DNADiscSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return isDNADisc(stack);
	}
	
	public boolean isDNADisc(ItemStack stack)
	{
		if(stack.getItem() instanceof DiscItem)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
