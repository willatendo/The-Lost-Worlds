package lostworlds.integration.buzzierbees.block.builder;

import net.minecraft.block.Block;

public class BuzzierBeesBlockAndItemBuilder 
{
	public static Block create(String id, Block block)
	{
		//BlockItem blockItem = new BlockItem(block, new Properties().tab(ModItemGroup.BLOCKS));
		//BuzzierBeesRegistry.register(id, block);
		//BuzzierBeesRegistry.register(id, blockItem);
		return block;
	}
}
