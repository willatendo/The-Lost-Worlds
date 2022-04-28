package lostworlds.server.block.tree;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class CustomTree {
	protected abstract Feature getFeature(Random random);

	public boolean place(ISeedReader reader, ChunkGenerator generator, BlockPos pos, BlockState blockUnder, Random random) {
		Feature tree = getFeature(random);
		if (tree == null)
			return false;

		reader.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
		if (tree.place(reader, generator, random, pos, new NoFeatureConfig())) {
			return true;
		}

		reader.setBlock(pos, blockUnder, 4);
		return false;
	}
}
