package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

/*
 * Author: Willatendo
 * Date: July 4, 2021
 */

public class SticksBlock extends Block
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 2, 16);

	public SticksBlock(Properties properties) 
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		return Direction.DOWN == direction && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		return blockstate.is(Blocks.AIR) ? false : blockstate.is(Blocks.WATER) ? false : blockstate.is(Blocks.LAVA) ? false : blockstate.getBlock() instanceof SticksBlock ? false : true;
	}
	
	@Override
	public PushReaction getPistonPushReaction(BlockState state) 
	{
		return PushReaction.DESTROY;
	}
}
