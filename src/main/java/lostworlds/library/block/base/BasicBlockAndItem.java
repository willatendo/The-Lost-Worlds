package lostworlds.library.block.base;

import lostworlds.library.item.basic.BasicBlockItem;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 16, 2021
 */

public class BasicBlockAndItem
{
	public static Block create(String id, Block block)
	{
		Item item = new BasicBlockItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
