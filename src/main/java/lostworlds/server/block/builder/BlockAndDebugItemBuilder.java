package lostworlds.server.block.builder;

import lostworlds.server.LostWorldsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockAndDebugItemBuilder {
	public static Block create(String id, Block block) {
		Item item = new DebugBlockItemBuilder(block);
		LostWorldsRegistry.register(id, block);
		LostWorldsRegistry.register(id, item);
		return block;
	}
}
