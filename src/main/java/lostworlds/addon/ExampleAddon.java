package lostworlds.addon;

import lostworlds.library.tab.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

@RegisterAddon()
public class ExampleAddon extends LostWorldsAddon
{
	public static final String ID = "example";
	public static final Item EXAMPLE_ITEM = new Item(new Properties().tab(ModItemGroup.ITEMS));
	
	static
	{
		register("example_item", EXAMPLE_ITEM);
	}
	
	@Override
	public String addonId() 
	{
		return ID;
	}

	@Override
	public String addonLoadMessage() 
	{
		return "LoadedAddon";
	}
	
	public static Item register(String id, Item item)
	{
		item.setRegistryName(rL(id));
		ForgeRegistries.ITEMS.register(item);
		return item;
	}

	private static ResourceLocation rL(String path)
	{
		return new ResourceLocation(ID, path);
	}
}
