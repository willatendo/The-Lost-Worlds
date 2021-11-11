package lostworlds.library.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

public abstract class ExtendableStemBlock extends SixWayBlock 
{	
	public ExtendableStemBlock(Properties properties) 
	{
		super(0.3125F, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
	}
	
	public abstract Block flower();

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
	}

	public BlockState getStateForPlacement(IBlockReader reader, BlockPos pos) 
	{
		Block block = reader.getBlockState(pos.below()).getBlock();
		Block block1 = reader.getBlockState(pos.above()).getBlock();
		Block block2 = reader.getBlockState(pos.north()).getBlock();
		Block block3 = reader.getBlockState(pos.east()).getBlock();
		Block block4 = reader.getBlockState(pos.south()).getBlock();
		Block block5 = reader.getBlockState(pos.west()).getBlock();
		return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(block == this || block == this.flower() || block.is(Tags.Blocks.DIRT))).setValue(UP, Boolean.valueOf(block1 == this || block1 == this.flower())).setValue(NORTH, Boolean.valueOf(block2 == this || block2 == this.flower())).setValue(EAST, Boolean.valueOf(block3 == this || block3 == this.flower())).setValue(SOUTH, Boolean.valueOf(block4 == this || block4 == this.flower())).setValue(WEST, Boolean.valueOf(block5 == this || block5 == this.flower()));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, IWorld world, BlockPos pos, BlockPos newpos) 
	{	
		if(!state.canSurvive(world, pos)) 
		{
			world.getBlockTicks().scheduleTick(pos, this, 1);
			return super.updateShape(state, direction, newstate, world, pos, newpos);
		} 
		else 
		{
			boolean flag = newstate.getBlock() == this || newstate.is(this.flower()) || direction == Direction.DOWN && newstate.is(Tags.Blocks.DIRT);
			return state.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
	{
		if(!state.canSurvive(world, pos)) 
		{
			world.destroyBlock(pos, true);
		}

	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		boolean flag = !reader.getBlockState(pos.above()).isAir() && !blockstate.isAir();

		for(Direction direction : Direction.Plane.HORIZONTAL) 
		{
			BlockPos blockpos = pos.relative(direction);
			Block block = reader.getBlockState(blockpos).getBlock();
			if(block == this) 
			{
				if(flag) 
				{
					return false;
				}

				Block block1 = reader.getBlockState(blockpos.below()).getBlock();
				if(block1 == this || block1.is(Tags.Blocks.DIRT))
				{
					return true;
				}
			}
		}

		Block block2 = blockstate.getBlock();
		return block2 == this || block2.is(Tags.Blocks.DIRT);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType type) 
	{
		return false;
	}
}
