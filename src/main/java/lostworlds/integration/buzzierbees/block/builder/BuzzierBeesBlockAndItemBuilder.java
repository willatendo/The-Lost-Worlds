package lostworlds.integration.buzzierbees.block.builder;

import lostworlds.integration.buzzierbees.util.BuzzierBeesRegistry;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Properties;

public class BuzzierBeesBlockAndItemBuilder 
{
	public static Block create(String id, Block block)
	{
		BlockItem blockItem = new BlockItem(block, new Properties().tab(ModItemGroup.BLOCKS));
		BuzzierBeesRegistry.register(id, block);
		BuzzierBeesRegistry.register(id, blockItem);
		return block;
	}
}
