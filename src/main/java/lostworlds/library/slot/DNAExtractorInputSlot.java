package lostworlds.library.slot;

import lostworlds.library.item.PlantSoftTissueItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class DNAExtractorInputSlot extends Slot
{
	public DNAExtractorInputSlot(IInventory inventory, int x, int z, int y) 
	{
		super(inventory, x, z, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return stack.getItem() instanceof PlantSoftTissueItem;
	}
}
