package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.GeyserBlock;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;

public class GeyserBlockFeature extends Feature<CountConfiguration> {
	public GeyserBlockFeature(Codec<CountConfiguration> codec) {
		super(codec);
	}

	public boolean place(WorldGenLevel seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, CountConfiguration config) {
		int i = 0;
		int j = config.count().sample(rand);

		for (int k = 0; k < j; ++k) {
			int l = rand.nextInt(8) - rand.nextInt(8);
			int i1 = rand.nextInt(8) - rand.nextInt(8);
			int j1 = seedReader.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX() + l, pos.getZ() + i1);
			BlockPos blockpos = new BlockPos(pos.getX() + l, j1, pos.getZ() + i1);
			BlockState blockstateWithLava = LostWorldsBlocks.GEYSER_BLOCK.getDefaultState().setValue(GeyserBlock.LAVALOGGED, Boolean.valueOf(true));
			BlockState blockstate = LostWorldsBlocks.GEYSER_BLOCK.getDefaultState().setValue(GeyserBlock.LAVALOGGED, Boolean.valueOf(false));
			if (seedReader.getBlockState(blockpos).is(Blocks.LAVA) && blockstateWithLava.canSurvive(seedReader, blockpos)) {
				seedReader.setBlock(blockpos, blockstateWithLava, 2);
				++i;
			}
			if (!seedReader.getBlockState(blockpos).is(Blocks.LAVA) && blockstate.canSurvive(seedReader, blockpos)) {
				seedReader.setBlock(blockpos, blockstate, 2);
				++i;
			}
		}

		return i > 0;
	}
}
