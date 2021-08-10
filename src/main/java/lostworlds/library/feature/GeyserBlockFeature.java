package lostworlds.library.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.GeyserBlock;
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
 * Date: July 10, 2021
 */

public class GeyserBlockFeature extends Feature<FeatureSpreadConfig> 
{
	public GeyserBlockFeature(Codec<FeatureSpreadConfig> codec) 
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
			int j1 = seedReader.getHeight(Heightmap.Type.WORLD_SURFACE, pos.getX() + l, pos.getZ() + i1);
			BlockPos blockpos = new BlockPos(pos.getX() + l, j1, pos.getZ() + i1);
			BlockState blockstateWithLava = BlockInit.GEYSER_BLOCK.defaultBlockState().setValue(GeyserBlock.LAVALOGGED, Boolean.valueOf(true));
			BlockState blockstate = BlockInit.GEYSER_BLOCK.defaultBlockState().setValue(GeyserBlock.LAVALOGGED, Boolean.valueOf(false));
			if(seedReader.getBlockState(blockpos).is(Blocks.LAVA) && blockstateWithLava.canSurvive(seedReader, blockpos)) 
			{
				seedReader.setBlock(blockpos, blockstateWithLava, 2);
				++i;
			}
			if(!seedReader.getBlockState(blockpos).is(Blocks.LAVA) && blockstate.canSurvive(seedReader, blockpos)) 
			{
				seedReader.setBlock(blockpos, blockstate, 2);
				++i;
			}
		}

		return i > 0;
	}
}
