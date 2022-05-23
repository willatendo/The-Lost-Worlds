package lostworlds.server.feature;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class AncientSpringFeature extends Feature<NoneFeatureConfiguration> {
	public AncientSpringFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		HolderSet<Block> validBlocks = HolderSet.direct(Block::builtInRegistryHolder, LostWorldsBlocks.PERMIAN_STONE.get(), LostWorldsBlocks.JURASSIC_STONE.get(), Blocks.STONE);
		WorldGenLevel worldgenlevel = context.level();
		BlockPos blockpos = context.origin();
		if (!worldgenlevel.getBlockState(blockpos.above()).is(validBlocks)) {
			return false;
		} else if (!worldgenlevel.getBlockState(blockpos.below()).is(validBlocks)) {
			return false;
		} else {
			BlockState blockstate = worldgenlevel.getBlockState(blockpos);
			if (!blockstate.isAir() && !blockstate.is(validBlocks)) {
				return false;
			} else {
				int i = 0;
				int j = 0;
				if (worldgenlevel.getBlockState(blockpos.west()).is(validBlocks)) {
					++j;
				}

				if (worldgenlevel.getBlockState(blockpos.east()).is(validBlocks)) {
					++j;
				}

				if (worldgenlevel.getBlockState(blockpos.north()).is(validBlocks)) {
					++j;
				}

				if (worldgenlevel.getBlockState(blockpos.south()).is(validBlocks)) {
					++j;
				}

				if (worldgenlevel.getBlockState(blockpos.below()).is(validBlocks)) {
					++j;
				}

				int k = 0;
				if (worldgenlevel.isEmptyBlock(blockpos.west())) {
					++k;
				}

				if (worldgenlevel.isEmptyBlock(blockpos.east())) {
					++k;
				}

				if (worldgenlevel.isEmptyBlock(blockpos.north())) {
					++k;
				}

				if (worldgenlevel.isEmptyBlock(blockpos.south())) {
					++k;
				}

				if (worldgenlevel.isEmptyBlock(blockpos.below())) {
					++k;
				}

				if (j == 4 && k == 1) {
					worldgenlevel.setBlock(blockpos, Fluids.WATER.defaultFluidState().createLegacyBlock(), 2);
					worldgenlevel.scheduleTick(blockpos, Fluids.WATER.defaultFluidState().getType(), 0);
					++i;
				}

				return i > 0;
			}
		}
	}
}
