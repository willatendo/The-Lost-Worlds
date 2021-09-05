package lostworlds.library.item;

import lostworlds.library.tab.ItemCategories;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class CrystalScarabGemItem extends Item implements ISortableItem
{	
	public static Item charged_crystal_scarab_gem = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.CHARGED);
	public static Item crystal_scarab_gem = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.UNCHARGED);
	
	public static Item crystal_scarab_abdomen = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_bottom_left_leg = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_bottom_right_leg = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_thorax = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_top_left_leg = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_top_right_leg = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	
	private Variant variant;
	
	protected CrystalScarabGemItem(Properties properties, Variant variant)
	{
		super(properties);
		this.variant = variant;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		if(this.variant != Variant.CHARGED)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static Item createAll()
	{
		ModRegistry.register("charged_crystal_scarab_gem", charged_crystal_scarab_gem);
		ModRegistry.register("crystal_scarab_gem", crystal_scarab_gem);

		ModRegistry.register("crystal_scarab_abdomen", crystal_scarab_abdomen);
		ModRegistry.register("crystal_scarab_bottom_left_leg", crystal_scarab_bottom_left_leg);
		ModRegistry.register("crystal_scarab_bottom_right_leg", crystal_scarab_bottom_right_leg);
		ModRegistry.register("crystal_scarab_thorax", crystal_scarab_thorax);
		ModRegistry.register("crystal_scarab_top_left_leg", crystal_scarab_top_left_leg);
		ModRegistry.register("crystal_scarab_top_right_leg", crystal_scarab_top_right_leg);

		return charged_crystal_scarab_gem;
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.ARCHAEOLOGY;
	}
	
	public enum Variant
	{
		BROKEN,
		UNCHARGED,
		CHARGED;
	}
}
