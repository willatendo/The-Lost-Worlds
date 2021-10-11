package lostworlds.library.biome.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class JurassicErrodedMountainsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> 
{
	public JurassicErrodedMountainsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) 
	{
		super(codec);
	}
	
	@Override
	public void apply(Random rand, IChunk iChunk, Biome biome, int x, int z, int startHeight, double noise, BlockState block, BlockState fluid, int seaLevel, long seed, SurfaceBuilderConfig config) 
	{
		if(noise > 1.0D) 
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilders.JURASSIC_COBBLESTONE_CONFIG);
		}
		else
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilders.SAND_CONFIG);
		}
	}
}
