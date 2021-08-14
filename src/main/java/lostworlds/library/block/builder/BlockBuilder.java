package lostworlds.library.block.builder;

import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class BlockBuilder 
{
	public static Block create(String id, Block block)
	{
		ModRegistry.register(id, block);
		return block;
	}
}
