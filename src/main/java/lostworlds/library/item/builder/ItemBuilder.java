package lostworlds.library.item.builder;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class ItemBuilder extends Item
{
	protected ItemBuilder() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}

	public static Item create(String id)
	{
		Item item = new ItemBuilder();
		ModRegistry.register(id, item);
		return item;
	}

	public static Item create(String id, Item item)
	{
		ModRegistry.register(id, item);
		return item;
	}
}
