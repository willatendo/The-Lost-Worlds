package lostworlds.integration.quark.util;

import lostworlds.integration.CompatibilityRegistries;
import lostworlds.integration.quark.init.QuarkBlocks;
import lostworlds.integration.quark.init.QuarkItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class QuarkRegistry 
{	
	public static Item register(String id, Item item)
	{
		return CompatibilityRegistries.register("quark", id, item);
	}
	
	public static Block register(String id, Block block)
	{
		return CompatibilityRegistries.register("quark", id, block);
	}
	
	public static void register()
	{
		QuarkItems.init();
		QuarkBlocks.init();
	}
}
