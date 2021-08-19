package lostworlds.integration.buzzierbees.util;

import lostworlds.integration.CompatibilityRegistries;
import lostworlds.integration.buzzierbees.init.BuzzierBeesBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BuzzierBeesRegistry 
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
		BuzzierBeesBlocks.init();
	}
}
