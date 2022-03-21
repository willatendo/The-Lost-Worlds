package lostworlds.server.block.builder;

import lostworlds.server.LostWorldsRegistry;
import net.minecraft.block.Block;

public class BlockBuilder {
	public static Block create(String id, Block block) {
		LostWorldsRegistry.register(id, block);
		return block;
	}
}
