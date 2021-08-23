package lostworlds.library.block.builder;

import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;

public class BlockBuilder
{
	public static Block create(String id, Block block) 
	{
		ModRegistry.register(id, block);
		return block;
	}
}
