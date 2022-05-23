package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.SpongeColonyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;

public class SpongeColoneyFeature extends Feature<CountConfiguration> {
	public SpongeColoneyFeature(Codec<CountConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<CountConfiguration> context) {
		CountConfiguration config = context.config();
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = new Random();
		int i = 0;
		int j = config.count().sample(rand);

		for (int k = 0; k < j; ++k) {
			int l = rand.nextInt(8) - rand.nextInt(8);
			int i1 = rand.nextInt(8) - rand.nextInt(8);
			int j1 = level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + l, pos.getZ() + i1);
			BlockPos blockpos = new BlockPos(pos.getX() + l, j1, pos.getZ() + i1);
			BlockState blockstate = LostWorldsBlocks.SPONGE_COLONY.getDefaultState().setValue(SpongeColonyBlock.WATERLOGGED, Boolean.valueOf(true));
			if (level.getBlockState(blockpos).is(Blocks.WATER) && blockstate.canSurvive(level, blockpos)) {
				level.setBlock(blockpos, blockstate, 2);
				++i;
			}
		}

		return i > 0;
	}
}
