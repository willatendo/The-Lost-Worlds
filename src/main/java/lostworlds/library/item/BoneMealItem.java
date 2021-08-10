package lostworlds.library.item;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class BoneMealItem extends net.minecraft.item.BoneMealItem
{
	protected BoneMealItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	public static Item create(String id)
	{
		Item item = new BoneMealItem();
		ModRegistry.register(id, item);
		return item;
	}
}
