package lostworlds.library.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AshFeature extends Feature<NoFeatureConfig>
{
	public AshFeature(Codec<NoFeatureConfig> codec) 
	{
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) 
	{
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();
		
		for(int i = 0; i < 16; ++i) 
		{
			for(int j = 0; j < 16; ++j) 
			{
				int k = pos.getX() + i;
				int l = pos.getZ() + j;
				int i1 = reader.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
				blockpos$mutable.set(k, i1, l);
				blockpos$mutable1.set(blockpos$mutable).move(Direction.DOWN, 1);
				Biome biome = reader.getBiome(blockpos$mutable);
				if(!biome.shouldFreeze(reader, blockpos$mutable1, false) && !biome.shouldSnow(reader, blockpos$mutable1))
				{
					reader.setBlock(blockpos$mutable, BlockInit.VOLCANIC_ASH_LAYER.defaultBlockState(), 2);				
				}
			}
		}
		
		return true;
	}
}
