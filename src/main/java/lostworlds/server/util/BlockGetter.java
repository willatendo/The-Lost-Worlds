package lostworlds.server.util;

import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockGetter {
	public static Block getWhenCan(BlockEntry entry) {
		return ForgeRegistries.BLOCKS.getValue(entry.getId());
	}

	public static BlockState getStateWhenCan(BlockEntry entry) {
		return ForgeRegistries.BLOCKS.getValue(entry.getId()).defaultBlockState();
	}
}
