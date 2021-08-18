package lostworlds.integration.quark.item.builder;

import lostworlds.integration.quark.util.QuarkRegistry;
import net.minecraft.item.Item;

public class QuarkItemBuilder 
{
	public static Item create(String id, Item item)
	{
		QuarkRegistry.register(id, item);
		return item;
	}
}
