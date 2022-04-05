package lostworlds.server.block.builder;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockBuilder {
	public static Block create(String id, Block block) {
		block.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.BLOCKS.register(block);
		return block;
	}
}
