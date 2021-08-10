package lostworlds.library.slot;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class PlasteredFossilSlot extends Slot
{
	public PlasteredFossilSlot(IInventory inventory, int x, int z, int y) 
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
		return stack.getItem() == BlockInit.PLASTERED_FOSSIL.asItem();
	}
}
