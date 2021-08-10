package lostworlds.library.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.SpongeColonyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;

/*
 * Author: Willatendo
 * Date: July 12, 2021
 */

public class SpongeColoneyFeature extends Feature<FeatureSpreadConfig> 
{
	public SpongeColoneyFeature(Codec<FeatureSpreadConfig> codec) 
	{
		super(codec);
	}

	public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, FeatureSpreadConfig config) 
	{
		int i = 0;
		int j = config.count().sample(rand);

		for(int k = 0; k < j; ++k) 
		{
			int l = rand.nextInt(8) - rand.nextInt(8);
			int i1 = rand.nextInt(8) - rand.nextInt(8);
			int j1 = seedReader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + l, pos.getZ() + i1);
			BlockPos blockpos = new BlockPos(pos.getX() + l, j1, pos.getZ() + i1);
			BlockState blockstate = BlockInit.SPONGE_COLONY.defaultBlockState().setValue(SpongeColonyBlock.WATERLOGGED, Boolean.valueOf(true));
			if(seedReader.getBlockState(blockpos).is(Blocks.WATER) && blockstate.canSurvive(seedReader, blockpos)) 
			{
				seedReader.setBlock(blockpos, blockstate, 2);
				++i;
			}
		}

		return i > 0;
	}
}
