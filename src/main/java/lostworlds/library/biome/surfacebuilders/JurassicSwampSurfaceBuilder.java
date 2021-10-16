package lostworlds.library.biome.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class JurassicSwampSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> 
{
	public JurassicSwampSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) 
	{
		super(codec);
	}
	
	@Override
	public void apply(Random rand, IChunk iChunk, Biome biome, int x, int z, int startHeight, double noise, BlockState block, BlockState fluid, int seaLevel, long seed, SurfaceBuilderConfig config) 
	{
		double d0 = Biome.BIOME_INFO_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D, false);
		if(d0 > 0.0D) 
		{
			int i = x & 15;
			int j = z & 15;
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
			
			for(int k = seaLevel; k >= 0; --k) 
			{
				blockpos$mutable.set(i, k, j);
				if(!iChunk.getBlockState(blockpos$mutable).isAir()) 
				{
					if(k == 62 && !iChunk.getBlockState(blockpos$mutable).is(fluid.getBlock())) 
					{
						iChunk.setBlockState(blockpos$mutable, fluid, false);
					}
					break;
				}
			}
		}
		
		if(noise > 1.0D) 
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilders.PODZOL_CONFIG); 
		}
		else if(noise < 1.0D) 
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilders.MUD_CONFIG); 
		}
		else
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, x, z, startHeight, noise, block, fluid, seaLevel, seed, ModSurfaceBuilders.MOSSY_SOIL_SILT_CONFIG);
		}
	}
}
