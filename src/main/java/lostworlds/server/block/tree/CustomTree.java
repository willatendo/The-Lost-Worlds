package lostworlds.server.block.tree;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public abstract class CustomTree {
	protected abstract Feature getFeature(Random random);

	public boolean place(WorldGenLevel reader, ChunkGenerator generator, BlockPos pos, BlockState blockUnder, Random random) {
		Feature tree = getFeature(random);
		if (tree == null)
			return false;

		reader.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
		if (tree.place(reader, generator, random, pos, new NoneFeatureConfiguration())) {
			return true;
		}

		reader.setBlock(pos, blockUnder, 4);
		return false;
	}
}
