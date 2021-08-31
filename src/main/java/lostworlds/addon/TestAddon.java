package lostworlds.addon;

import lostworlds.library.tab.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;

@Register
public class TestAddon extends LostWorldsAddon
{
	public static final Item TEST_ITEM = new Item(new Properties().tab(ModItemGroup.ITEMS));

	static
	{
		ID = "test";
		
		register("test_item", TEST_ITEM);
	}
}
