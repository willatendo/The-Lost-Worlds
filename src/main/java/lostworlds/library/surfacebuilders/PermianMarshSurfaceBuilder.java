package lostworlds.library.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class PermianMarshSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> 
{
	public PermianMarshSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) 
	{
		super(codec);
	}
	
	@Override
	public void apply(Random rand, IChunk iChunk, Biome biome, int i1, int i2, int i3, double d, BlockState state1, BlockState state2, int i4, long l, SurfaceBuilderConfig config) 
	{
		double d0 = Biome.BIOME_INFO_NOISE.getValue((double)i1 * 0.25D, (double)i2 * 0.25D, false);
		if(d0 > 0.0D) 
		{
			int i = i1 & 15;
			int j = i2 & 15;
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
			
			for(int k = i3; k >= 0; --k) 
			{
				blockpos$mutable.set(i, k, j);
				if(!iChunk.getBlockState(blockpos$mutable).isAir()) 
				{
					if(k == 62 && !iChunk.getBlockState(blockpos$mutable).is(state2.getBlock())) 
					{
						iChunk.setBlockState(blockpos$mutable, state2, false);
					}
					break;
				}
			}
		}
		
		if(d > 1.0D) 
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, i1, i2, i3, d, state1, state2, i4, l, ModSurfaceBuilders.MUD_CONFIG);
		}
		else
		{
			SurfaceBuilder.DEFAULT.apply(rand, iChunk, biome, i1, i2, i3, d, state1, state2, i4, l, ModSurfaceBuilders.MOSSY_SOIL_SILT_CONFIG);
		}
	}
}
