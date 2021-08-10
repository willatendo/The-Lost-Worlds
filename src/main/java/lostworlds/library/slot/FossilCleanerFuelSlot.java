package lostworlds.library.slot;

import lostworlds.library.tileentity.FossilCleanerTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class FossilCleanerFuelSlot extends Slot
{
	public FossilCleanerFuelSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return FossilCleanerTileEntity.isFuel(stack);
	}
}
