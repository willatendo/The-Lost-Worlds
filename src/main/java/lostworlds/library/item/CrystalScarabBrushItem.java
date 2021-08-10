package lostworlds.library.item;

import lostworlds.library.item.BrushItem.Teirs;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class CrystalScarabBrushItem extends AxeItem
{
	public CrystalScarabBrushItem() 
	{
		super(Teirs.CRYSTAL_SCARAB, 70.0F, -3.2F, new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE).setNoRepair().fireResistant());
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
	{
		return false;
	}
	
	public static Item create()
	{
		Item item = new CrystalScarabBrushItem();
		ModRegistry.register("crystal_scarab_brush", item);
		return item;
	}
}
