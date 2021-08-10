package lostworlds.library.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class EggSlot extends Slot
{
	public EggSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return isVile(stack);
	}
	
	public boolean isVile(ItemStack stack)
	{
		return stack.getItem() == Items.EGG;
	}
}
