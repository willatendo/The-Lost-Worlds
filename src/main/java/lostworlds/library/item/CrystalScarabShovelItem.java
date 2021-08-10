package lostworlds.library.item;

import lostworlds.library.enums.ModItemTeir;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class CrystalScarabShovelItem extends ShovelItem
{
	public CrystalScarabShovelItem() 
	{
		super(ModItemTeir.CRYSTAL_SCARAB, 12.0F, -3.0F, new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE).setNoRepair().fireResistant());
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
		Item item = new CrystalScarabShovelItem();
		ModRegistry.register("crystal_scarab_shovel", item);
		return item;
	}
}
