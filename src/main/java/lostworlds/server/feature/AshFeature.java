package lostworlds.server.feature;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AshFeature extends Feature<NoneFeatureConfiguration> {
	public AshFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();
		BlockPos.MutableBlockPos mutableblockpos1 = new BlockPos.MutableBlockPos();

		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				int k = pos.getX() + i;
				int l = pos.getZ() + j;
				int i1 = level.getHeight(Heightmap.Types.MOTION_BLOCKING, k, l);
				mutableblockpos.set(k, i1, l);
				mutableblockpos1.set(mutableblockpos).move(Direction.DOWN, 1);
				Biome biome = level.getBiome(mutableblockpos).value();
				if (!biome.shouldFreeze(level, mutableblockpos1, false) && !biome.shouldSnow(level, mutableblockpos1)) {
					level.setBlock(mutableblockpos, LostWorldsBlocks.VOLCANIC_ASH_LAYER.getDefaultState(), 2);
				}
			}
		}

		return true;
	}
}
