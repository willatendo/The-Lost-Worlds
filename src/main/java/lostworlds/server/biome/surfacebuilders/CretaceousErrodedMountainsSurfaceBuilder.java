package lostworlds.server.biome.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class CretaceousErrodedMountainsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
	public CretaceousErrodedMountainsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
		super(codec);
	}

	@Override
	public void apply(Random rand, ChunkAccess iChunk, Biome biome, int x, int z, int startHeight, double noise, BlockState block, BlockState fluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
		if (noise > 1.75D) {
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilderConfigs.DRIED_SOIL_CONFIG.get());
		} else {
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilderConfigs.COBBLESTONE_CONFIG.get());
		}
	}
}
