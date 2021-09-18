package lostworlds.library.block.builder;

import lostworlds.library.block.ConnectedTextureBlock;
import lostworlds.library.item.builder.BlockItemBuilder;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockAndItemBuilder
{
	public static Block create(String id, Block block)
	{
		Item item = new BlockItemBuilder(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
	
	public static ConnectedTextureBlock create(String id, ConnectedTextureBlock block)
	{
		Item item = new BlockItemBuilder(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
	
	public static Block create(String id, Block block, ItemGroup group)
	{
		Item item = new BlockItemBuilder(block, group);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
