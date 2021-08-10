package lostworlds.library.item;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

/*
 * Author: Willatendo
 * Date: July 10, 2021
 */

public class CrystalScarabGemItem extends Item
{	
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
	
	public static Item create(Variant varient)
	{
		if(varient != Variant.CHARGED)
		{
			Item item = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE), varient);
			ModRegistry.register(varient.toString().toLowerCase() + "_crystal_scarab_gem", item);
			return item;
		}
		else
		{
			Item item = new CrystalScarabGemItem(new Properties().tab(ModItemGroup.ITEMS).rarity(Rarity.RARE).fireResistant(), varient);
			ModRegistry.register(varient.toString().toLowerCase() + "_crystal_scarab_gem", item);
			return item;
		}
	}
	
	public enum Variant
	{
		BROKEN,
		UNCHARGED,
		CHARGED;
	}
	
	@FunctionalInterface
	public interface EntityFactory 
	{
		ItemEntity create(World w, double x, double y, double z, ItemStack is);
	}
}
