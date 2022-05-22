package lostworlds.server.feature;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AncientSpringFeature extends Feature<NoneFeatureConfiguration> {
	public AncientSpringFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		Set<Block> validBlocks = ImmutableSet.of(LostWorldsBlocks.PERMIAN_STONE.get(), LostWorldsBlocks.JURASSIC_STONE.get(), Blocks.STONE);
		if (!validBlocks.contains(reader.getBlockState(pos.above()).getBlock())) {
			return false;
		} else if (!validBlocks.contains(reader.getBlockState(pos.below()).getBlock())) {
			return false;
		} else {
			BlockState blockstate = reader.getBlockState(pos);
			if (!blockstate.isAir(reader, pos) && !validBlocks.contains(blockstate.getBlock())) {
				return false;
			} else {
				int i = 0;
				int j = 0;
				if (validBlocks.contains(reader.getBlockState(pos.west()).getBlock())) {
					++j;
				}

				if (validBlocks.contains(reader.getBlockState(pos.east()).getBlock())) {
					++j;
				}

				if (validBlocks.contains(reader.getBlockState(pos.north()).getBlock())) {
					++j;
				}

				if (validBlocks.contains(reader.getBlockState(pos.south()).getBlock())) {
					++j;
				}

				if (validBlocks.contains(reader.getBlockState(pos.below()).getBlock())) {
					++j;
				}

				int k = 0;
				if (reader.isEmptyBlock(pos.west())) {
					++k;
				}

				if (reader.isEmptyBlock(pos.east())) {
					++k;
				}

				if (reader.isEmptyBlock(pos.north())) {
					++k;
				}

				if (reader.isEmptyBlock(pos.south())) {
					++k;
				}

				if (reader.isEmptyBlock(pos.below())) {
					++k;
				}

				if (j == 4 && k == 1) {
					reader.setBlock(pos, Fluids.WATER.defaultFluidState().createLegacyBlock(), 2);
					reader.getLiquidTicks().scheduleTick(pos, Fluids.WATER.defaultFluidState().getType(), 0);
					++i;
				}

				return i > 0;
			}
		}
	}
}
