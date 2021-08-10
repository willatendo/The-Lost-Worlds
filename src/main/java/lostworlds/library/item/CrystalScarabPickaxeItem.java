package lostworlds.library.item;

import lostworlds.library.enums.ModItemTeir;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class CrystalScarabPickaxeItem extends PickaxeItem
{
	public CrystalScarabPickaxeItem() 
	{
		super(ModItemTeir.CRYSTAL_SCARAB, 32, -2.8F, new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE).setNoRepair().fireResistant());
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
		Item item = new CrystalScarabPickaxeItem();
		ModRegistry.register("crystal_scarab_pickaxe", item);
		return item;
	}
}
