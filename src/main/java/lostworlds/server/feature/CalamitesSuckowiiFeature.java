package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class CalamitesSuckowiiFeature extends Feature<ProbabilityFeatureConfiguration> {
	public CalamitesSuckowiiFeature(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
		BlockState trunk = LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState().setValue(BambooBlock.AGE, Integer.valueOf(1)).setValue(BambooBlock.LEAVES, BambooLeaves.NONE).setValue(BambooBlock.STAGE, Integer.valueOf(0));
		BlockState finalLarge = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooBlock.STAGE, Integer.valueOf(1));
		BlockState topLarge = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE);
		BlockState topSmall = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.SMALL);

		int i = 0;
		BlockPos blockpos = context.origin();
		WorldGenLevel worldgenlevel = context.level();
		Random random = context.random();
		ProbabilityFeatureConfiguration probabilityfeatureconfiguration = context.config();
		BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();
		BlockPos.MutableBlockPos blockpos$mutableblockpos1 = blockpos.mutable();
		if (worldgenlevel.isEmptyBlock(blockpos$mutableblockpos)) {
			if (Blocks.BAMBOO.defaultBlockState().canSurvive(worldgenlevel, blockpos$mutableblockpos)) {
				int j = random.nextInt(12) + 5;
				if (random.nextFloat() < probabilityfeatureconfiguration.probability) {
					int k = random.nextInt(4) + 1;

					for (int l = blockpos.getX() - k; l <= blockpos.getX() + k; ++l) {
						for (int i1 = blockpos.getZ() - k; i1 <= blockpos.getZ() + k; ++i1) {
							int j1 = l - blockpos.getX();
							int k1 = i1 - blockpos.getZ();
							if (j1 * j1 + k1 * k1 <= k * k) {
								blockpos$mutableblockpos1.set(l, worldgenlevel.getHeight(Heightmap.Types.WORLD_SURFACE, l, i1) - 1, i1);
								if (isDirt(worldgenlevel.getBlockState(blockpos$mutableblockpos1))) {
									worldgenlevel.setBlock(blockpos$mutableblockpos1, Blocks.PODZOL.defaultBlockState(), 2);
								}
							}
						}
					}
				}

				for (int l1 = 0; l1 < j && worldgenlevel.isEmptyBlock(blockpos$mutableblockpos); ++l1) {
					worldgenlevel.setBlock(blockpos$mutableblockpos, trunk, 2);
					blockpos$mutableblockpos.move(Direction.UP, 1);
				}

				if (blockpos$mutableblockpos.getY() - blockpos.getY() >= 3) {
					worldgenlevel.setBlock(blockpos$mutableblockpos, finalLarge, 2);
					worldgenlevel.setBlock(blockpos$mutableblockpos.move(Direction.DOWN, 1), topLarge, 2);
					worldgenlevel.setBlock(blockpos$mutableblockpos.move(Direction.DOWN, 1), topSmall, 2);
				}
			}

			++i;
		}

		return i > 0;
	}
}
