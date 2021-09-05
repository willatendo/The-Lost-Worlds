package lostworlds.library.block.builder;

import lostworlds.library.block.ModStandingSignBlock;
import lostworlds.library.block.ModWallSignBlock;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;

public class BlockBuilder
{
	public static Block create(String id, Block block) 
	{
		ModRegistry.register(id, block);
		return block;
	}
	
	public static ModStandingSignBlock create(String id, ModStandingSignBlock block) 
	{
		ModRegistry.register(id, block);
		return block;
	}
	
	public static ModWallSignBlock create(String id, ModWallSignBlock block) 
	{
		ModRegistry.register(id, block);
		return block;
	}
}
