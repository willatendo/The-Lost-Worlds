package lostworlds.library.item.basic;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 16, 2021
 */

public class BasicItem extends Item
{
	protected BasicItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}

	public static Item create(String id)
	{
		Item item = new BasicItem();
		ModRegistry.register(id, item);
		return item;
	}
}
