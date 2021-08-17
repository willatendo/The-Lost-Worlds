package lostworlds.integration.quark.block.builder;

import lostworlds.integration.quark.util.QuarkRegistry;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Properties;

public class QuarkBlockAndItemBuilder 
{
	public static Block create(String id, Block block)
	{
		BlockItem blockItem = new BlockItem(block, new Properties().tab(ModItemGroup.BLOCKS));
		QuarkRegistry.register(id, block);
		QuarkRegistry.register(id, blockItem);
		return block;
	}
}
