package lostworlds.library.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class PermianAshyMedowsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> 
{
	public PermianAshyMedowsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) 
	{
		super(codec);
	}
	
	@Override
	public void apply(Random rand, IChunk iChunk, Biome biome, int i1, int i2, int i3, double d, BlockState state1, BlockState state2, int i4, long l, SurfaceBuilderConfig config) 
	{
		if(d > 1.0D) 
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, i1, i2, i3, d, state1, state2, i4, l, ModSurfaceBuilders.MOSSY_DIRT_CONFIG);
		}
		else
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, i1, i2, i3, d, state1, state2, i4, l, ModSurfaceBuilders.VOLCANIC_ASH_CONFIG);
		}
	}
}
