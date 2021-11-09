package lostworlds.library.block.builder;

import lostworlds.content.ModRegistry;
import lostworlds.library.block.ConnectedTextureBlock;
import lostworlds.library.item.EggBlockItem;
import lostworlds.library.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;

public class BlockAndItemBuilder
{
	public static Block create(String id, Block block, Item item)
	{
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
	
	public static Block create(String id, Block block)
	{
		Item item = new GroupedBlockItem(block);
		return create(id, block, item);
	}
	
	public static Block createEgg(String id, Block block, ITextComponent name)
	{
		Item item = new EggBlockItem(block, name);
		return create(id, block, item);
	}
	
	public static ConnectedTextureBlock create(String id, ConnectedTextureBlock block)
	{
		Item item = new GroupedBlockItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
