package lostworlds.library.block.builder;

import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockAndDebugItemBuilder 
{
	public static Block create(String id, Block block)
	{
		Item item = new DebugBlockItemBuilder(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
