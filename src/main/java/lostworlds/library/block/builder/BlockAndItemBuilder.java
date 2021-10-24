package lostworlds.library.block.builder;

import lostworlds.content.ModRegistry;
import lostworlds.library.block.ConnectedTextureBlock;
import lostworlds.library.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockAndItemBuilder
{
	public static Block create(String id, Block block)
	{
		Item item = new GroupedBlockItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
	
	public static ConnectedTextureBlock create(String id, ConnectedTextureBlock block)
	{
		Item item = new GroupedBlockItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
