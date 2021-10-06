package lostworlds.library.container.slot;

import lostworlds.library.item.FossilItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class PlasteredFossilSlot extends Slot
{
	public PlasteredFossilSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return stack.getItem() instanceof FossilItem;
	}
}
