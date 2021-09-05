package lostworlds.library.item.tool;

import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrystalScarabGemBrushItem extends BrushItem
{
	protected CrystalScarabGemBrushItem() 
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

	public static Item create()
	{
		Item item = new CrystalScarabGemBrushItem();
		ModRegistry.register(ModItemTier.CRYSTAL_SCARAB.toString().toLowerCase() + "_brush", item);
		return item;
	}
}
