package lostworlds.library.item.tool;

import net.minecraft.item.ItemStack;

public class CrystalScarabGemBrushItem extends BrushItem
{
	public CrystalScarabGemBrushItem() 
	{
		super(ModItemTier.CRYSTAL_SCARAB, new Properties().setNoRepair());
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) 
	{
		return true;
	}
}
