package lostworlds.library.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.IPlantable;

public class DriedSoilBlock extends Block
{
	public DriedSoilBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
	{
		if(!isNearWater(world, pos) && !world.isRainingAt(pos.above())) 
		{
			return;
		} 
		else
		{
			turnToDirt(state, world, pos);
		}
	}
	
	private static boolean isNearWater(IWorldReader render, BlockPos pos) 
	{
		for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) 
		{
			if (render.getFluidState(blockpos).is(FluidTags.WATER)) 
			{
				return true;
			}
		}
		
		return FarmlandWaterManager.hasBlockWaterTicket(render, pos);
	}
	
	public static void turnToDirt(BlockState state, World world, BlockPos pos) 
	{
		world.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) 
	{
		return true;
	}
}
