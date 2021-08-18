package lostworlds.integration.quark.block.builder;

import lostworlds.integration.quark.util.QuarkRegistry;
import net.minecraft.block.Block;

public class QuarkBlockBuilder 
{
	public static Block create(String id, Block block)
	{
		QuarkRegistry.register(id, block);
		return block;
	}
}
