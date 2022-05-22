package lostworlds.server.biome.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class JurassicSwampSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
	public JurassicSwampSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
		super(codec);
	}

	@Override
	public void apply(Random rand, ChunkAccess iChunk, Biome biome, int x, int z, int startHeight, double noise, BlockState block, BlockState fluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
		double d0 = Biome.BIOME_INFO_NOISE.getValue((double) x * 0.25D, (double) z * 0.25D, false);
		if (d0 > 0.0D) {
			int i = x & 15;
			int j = z & 15;
			BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

			for (int k = seaLevel; k >= 0; --k) {
				blockpos$mutable.set(i, k, j);
				if (!iChunk.getBlockState(blockpos$mutable).isAir()) {
					if (k == 62 && !iChunk.getBlockState(blockpos$mutable).is(fluid.getBlock())) {
						iChunk.setBlockState(blockpos$mutable, fluid, false);
					}
					break;
				}
			}
		}

		if (noise > 1.0D) {
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilderConfigs.PODZOL_CONFIG.get());
		} else if (noise < 1.0D) {
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilderConfigs.MUD_CONFIG.get());
		} else {
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilderConfigs.MOSSY_SOIL_SILT_CONFIG.get());
		}
	}
}
