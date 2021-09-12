package lostworlds.library.item.builder;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

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

	public static Item create(String id, Properties properties)
	{
		Item item = new Item(properties);
		ModRegistry.register(id, item);
		return item;
	}

	public static Item create(String id, ItemGroup group)
	{
		Item item = new Item(new Properties().tab(group));
		ModRegistry.register(id, item);
		return item;
	}
}
