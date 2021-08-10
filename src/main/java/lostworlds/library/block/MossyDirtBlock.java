package lostworlds.library.block;

import java.util.List;
import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.base.BasicBlockAndItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class MossyDirtBlock extends Block implements IGrowable
{
	protected MossyDirtBlock() 
	{
		super(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).randomTicks().sound(SoundType.GRAVEL));
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
		if(!worldIn.isClientSide()) 
		{
			if(!worldIn.isAreaLoaded(pos, 3)) return;
			if(!isLightEnough(state, worldIn, pos)) 
			{
				worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			} 
			else if(worldIn.getMaxLocalRawBrightness(pos.above()) >= 9) 
			{
				BlockState blockstate = this.defaultBlockState();
				
				for(int i = 0; i < 4; ++i) 
				{
					BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if(worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && isValidBonemealTargetGrass(blockstate, worldIn, blockpos))
					{
						worldIn.setBlockAndUpdate(blockpos, blockstate);
					}
				}
			}
		}
	}
	
	private static boolean isLightEnough(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockPos blockpos = pos.above();
		BlockState blockstate = reader.getBlockState(blockpos);
		
		int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(reader, blockpos));
		return i < reader.getMaxLightLevel();
	}
	
	private static boolean isValidBonemealTargetGrass(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockPos blockpos = pos.above();
		return isLightEnough(state, reader, pos) && !reader.getFluidState(blockpos).is(FluidTags.WATER);
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) 
	{
		return true;
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) 
	{
		return true;
	}
	
	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}
	
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		BlockPos blockpos = pos.above();
		BlockState growOne = Blocks.FERN.defaultBlockState();
		BlockState growTwo = BlockInit.GROUND_FERNS.defaultBlockState();
		
		label48:
		for(int i = 0; i < 128; ++i) 
		{
			BlockPos blockpos1 = blockpos;
			
			for(int j = 0; j < i / 16; ++j) 
			{
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if(!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1)) 
				{
					continue label48;
				}
			}
			
			BlockState blockstate2 = worldIn.getBlockState(blockpos1);
			if(blockstate2.isAir()) 
			{
				BlockState blockstate1;
				if(rand.nextInt(8) == 0) 
				{
					List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
					if(list.isEmpty()) 
					{
						continue;
					}
					
					ConfiguredFeature<?, ?> configuredfeature = list.get(0);
					FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
					blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
				}
				else 
				{
					blockstate1 = rand.nextInt(2) == 0 ? growOne : growTwo;
				}
				
				if(blockstate1.canSurvive(worldIn, blockpos1)) 
				{
					worldIn.setBlockAndUpdate(blockpos1, blockstate1);
				}
			}
		}
	}
	
	public static Block create()
	{
		return BasicBlockAndItem.create("mossy_dirt", new MossyDirtBlock());
	}
}
