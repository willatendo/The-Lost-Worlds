package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class DisplayCaseBlock extends Block
{
	private static final EnumProperty<DisplayCaseSize> SIZE = EnumProperty.create("size", DisplayCaseSize.class);
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public DisplayCaseBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(SIZE, DisplayCaseSize.SINGLE).setValue(HORIZONTAL_FACING, Direction.NORTH));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) 
	{
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		boolean north = state.getValue(HORIZONTAL_FACING) == Direction.NORTH;
		boolean south = state.getValue(HORIZONTAL_FACING) == Direction.SOUTH;
		boolean east = state.getValue(HORIZONTAL_FACING) == Direction.EAST;
		boolean west = state.getValue(HORIZONTAL_FACING) == Direction.WEST;
		if(north)
		{
			boolean right = world.getBlockState(pos.east()).getBlock() == this;
			boolean left = world.getBlockState(pos.west()).getBlock() == this;
			if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(south)
		{
			boolean right = world.getBlockState(pos.west()).getBlock() == this;
			boolean left = world.getBlockState(pos.east()).getBlock() == this;
			if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(east)
		{
			boolean right = world.getBlockState(pos.south()).getBlock() == this;
			boolean left = world.getBlockState(pos.north()).getBlock() == this;
			if(right && left)
			{
				return 	state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(west)
		{
			boolean right = world.getBlockState(pos.north()).getBlock() == this;
			boolean left = world.getBlockState(pos.south()).getBlock() == this;
			if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		return super.updateShape(state, direction, newState, world, pos, newPos);
	}
	
	
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(SIZE, HORIZONTAL_FACING);
	}
}
