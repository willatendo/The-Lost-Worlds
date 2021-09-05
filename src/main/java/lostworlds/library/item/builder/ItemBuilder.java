package lostworlds.library.item.builder;

import lostworlds.library.item.SortedItem;
import lostworlds.library.tab.ItemCategories;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class ItemBuilder extends SortedItem
{
	protected ItemBuilder(ItemCategories category) 
	{
		super(category, new Properties().tab(ModItemGroup.ITEMS));
	}

	public static Item create(ItemCategories category, String id)
	{
		Item item = new ItemBuilder(category);
		ModRegistry.register(id, item);
		return item;
	}
}
