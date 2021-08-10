package lostworlds.library.block;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import lostworlds.library.ModBlockStateProperties;
import lostworlds.library.enums.RockSides;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Author: Willatendo
 * Date: July 5, 2021
 */

public class RockOutcropBlock extends Block
{
	public static final VoxelShape CENTRE = Block.box(0, 0, 0, 16, 10, 16);
	public static final VoxelShape NORTH = Stream.of(Block.box(0, 0, 10, 16, 10, 16), Block.box(0, 5, 3, 16, 10, 10), Block.box(0, 3, 3, 10, 5, 6), Block.box(6, 0, 7, 16, 2, 10), Block.box(7, 0, 6, 12, 2, 7), Block.box(10, 3, 5, 15, 5, 6), Block.box(0, 2, 6, 16, 5, 10)).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();
	public static final VoxelShape SOUTH = VoxelShapes.join(Block.box(13, 4, 0, 22, 10, 6), Block.box(3, 0, 0, 19, 4, 6), IBooleanFunction.OR);
	public static final VoxelShape EAST = VoxelShapes.join(Block.box(-1, 9, 10, 4, 10, 16), Block.box(0, 0, 0, 5, 9, 16), IBooleanFunction.OR);
	public static final VoxelShape WEST = Block.box(5, 0, 0, 16, 10, 16);
	public static final VoxelShape NORTH_WEST = Stream.of(Block.box(6, 0, 9, 16, 10, 16), Block.box(8, 3, 4, 16, 10, 9), Block.box(10, 1, 7, 20, 3, 10)).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();
	public static final VoxelShape SOUTH_WEST = VoxelShapes.join(Block.box(14, 0, 0, 19, 4, 5), Block.box(13, 4, 0, 29, 11, 6), IBooleanFunction.OR);
	
	public static final EnumProperty<RockSides> SIDES = ModBlockStateProperties.SIDES;

	public RockOutcropBlock(Properties propeties) 
	{
		super(propeties);
		this.registerDefaultState(this.stateDefinition.any().setValue(SIDES, RockSides.CENTRE));
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		return !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos blockpos = context.getClickedPos();
		return context.getLevel().getBlockState(blockpos).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.north()).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.south()).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.east()).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.west()).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.north().west()).canBeReplaced(context) && context.getLevel().getBlockState(blockpos.south().west()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		return blockstate.is(Blocks.AIR) ? false : blockstate.is(Blocks.WATER) ? false : blockstate.is(Blocks.LAVA) ? false : blockstate.getBlock() instanceof RockOutcropBlock ? false : blockstate.getBlock() instanceof LeavesBlock ? false : true;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		if(state.getValue(SIDES) == RockSides.CENTRE)
		{
			return CENTRE;
		}
		else if(state.getValue(SIDES) == RockSides.NORTH)
		{
			return NORTH;
		}
		else if(state.getValue(SIDES) == RockSides.SOUTH)
		{
			return SOUTH;
		}
		else if(state.getValue(SIDES) == RockSides.EAST)
		{
			return EAST;
		}
		else if(state.getValue(SIDES) == RockSides.WEST)
		{
			return WEST;
		}
		else if(state.getValue(SIDES) == RockSides.NORTH_WEST)
		{
			return NORTH_WEST;
		}
		else
		{
			return SOUTH_WEST;
		}
		
	}
	
	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) 
	{
		world.setBlock(pos, this.defaultBlockState().setValue(SIDES, RockSides.CENTRE), 3);
		world.setBlock(pos.north(), this.defaultBlockState().setValue(SIDES, RockSides.NORTH), 3);
		world.setBlock(pos.south(), this.defaultBlockState().setValue(SIDES, RockSides.SOUTH), 3);
		world.setBlock(pos.east(), this.defaultBlockState().setValue(SIDES, RockSides.EAST), 3);
		world.setBlock(pos.west(), this.defaultBlockState().setValue(SIDES, RockSides.WEST), 3);
		world.setBlock(pos.north().west(), this.defaultBlockState().setValue(SIDES, RockSides.NORTH_WEST), 3);
		world.setBlock(pos.south().west(), this.defaultBlockState().setValue(SIDES, RockSides.SOUTH_WEST), 3);
	}
	
	public void placeAt(IWorld world, BlockPos pos, int i) 
	{
		world.setBlock(pos, this.defaultBlockState().setValue(SIDES, RockSides.CENTRE), i);
		world.setBlock(pos.north(), this.defaultBlockState().setValue(SIDES, RockSides.NORTH), i);
		world.setBlock(pos.south(), this.defaultBlockState().setValue(SIDES, RockSides.SOUTH), i);
		world.setBlock(pos.east(), this.defaultBlockState().setValue(SIDES, RockSides.EAST), i);
		world.setBlock(pos.west(), this.defaultBlockState().setValue(SIDES, RockSides.WEST), i);
		world.setBlock(pos.north().west(), this.defaultBlockState().setValue(SIDES, RockSides.NORTH_WEST), i);
		world.setBlock(pos.south().west(), this.defaultBlockState().setValue(SIDES, RockSides.SOUTH_WEST), i);
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(SIDES);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) 
	{
		return MathHelper.getSeed(pos.getX(), pos.below(state.getValue(SIDES) == RockSides.CENTRE ? 0 : 1).getY(), pos.getZ());
	}
	
	@Override
	public PushReaction getPistonPushReaction(BlockState state) 
	{
		return PushReaction.DESTROY;
	}
}
