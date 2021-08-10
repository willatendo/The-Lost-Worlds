package lostworlds.library.interfaces;

import lostworlds.library.ModBlockStateProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

/*
 * Author: Willatendo
 * Date: July 9, 2021
 */

public interface ILavaLoggable extends IBucketPickupHandler, ILiquidContainer
{
	default boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid) 
	{
		return !state.getValue(ModBlockStateProperties.LAVALOGGED) && fluid == Fluids.LAVA;
	}
	
	default boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluid) 
	{
		if(!state.getValue(ModBlockStateProperties.LAVALOGGED) && fluid.getType() == Fluids.LAVA) 
		{
			if(!world.isClientSide()) 
			{
				world.setBlock(pos, state.setValue(ModBlockStateProperties.LAVALOGGED, Boolean.valueOf(true)), 3);
				world.getLiquidTicks().scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(world));
			}
			
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	default Fluid takeLiquid(IWorld world, BlockPos pos, BlockState state) 
	{
		if(state.getValue(ModBlockStateProperties.LAVALOGGED)) 
		{
			world.setBlock(pos, state.setValue(ModBlockStateProperties.LAVALOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} 
		else 
		{
			return Fluids.EMPTY;
		}
	}
}
