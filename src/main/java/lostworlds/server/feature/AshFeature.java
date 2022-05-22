package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AshFeature extends Feature<NoneFeatureConfiguration> {
	public AshFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(WorldGenLevel reader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		BlockPos.MutableBlockPos blockpos$mutable1 = new BlockPos.MutableBlockPos();

		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				int k = pos.getX() + i;
				int l = pos.getZ() + j;
				int i1 = reader.getHeight(Heightmap.Types.MOTION_BLOCKING, k, l);
				blockpos$mutable.set(k, i1, l);
				blockpos$mutable1.set(blockpos$mutable).move(Direction.DOWN, 1);
				Biome biome = reader.getBiome(blockpos$mutable);
				if (!biome.shouldFreeze(reader, blockpos$mutable1, false) && !biome.shouldSnow(reader, blockpos$mutable1)) {
					if (biome.getTemperature(pos) >= 5.0F) {
						reader.setBlock(blockpos$mutable, LostWorldsBlocks.VOLCANIC_ASH_LAYER.getDefaultState(), 2);
					}
				}
			}
		}

		return true;
	}
}
