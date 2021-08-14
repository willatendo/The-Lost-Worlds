package lostworlds.library.slot;

import lostworlds.content.server.init.ItemInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class DiscSlot extends Slot
{
	public DiscSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return stack.getItem() == ItemInit.STORAGE_DISC;
	}
}
