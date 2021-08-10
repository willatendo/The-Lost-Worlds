package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.library.ModBlockStateProperties;
import lostworlds.library.enums.TripleBlockHalfs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Author: Willatendo
 * Date: July 4, 2021
 */

public class TriplePlantBlock extends ModBushBlock
{
	public static final EnumProperty<TripleBlockHalfs> HALFS = ModBlockStateProperties.TRIPLE_HALFS;
	
	public TriplePlantBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALFS, TripleBlockHalfs.BOTTOM));
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		TripleBlockHalfs blockhalfs = state.getValue(HALFS);
		if(direction.getAxis() != Direction.Axis.Y || blockhalfs == TripleBlockHalfs.BOTTOM != (direction == Direction.UP) || newState.is(this) && newState.getValue(HALFS) != blockhalfs) 
		{
			return blockhalfs == TripleBlockHalfs.BOTTOM && direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
		}
		else if(direction.getAxis() != Direction.Axis.Y || blockhalfs == TripleBlockHalfs.BOTTOM != (direction == Direction.UP) || newState.is(this) && newState.getValue(HALFS) != blockhalfs) 
		{
			return blockhalfs == TripleBlockHalfs.BOTTOM && direction == Direction.UP && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
		}
		else if(direction.getAxis() != Direction.Axis.Y || blockhalfs == TripleBlockHalfs.MIDDLE != (direction == Direction.UP) || newState.is(this) && newState.getValue(HALFS) != blockhalfs) 
		{
			return blockhalfs == TripleBlockHalfs.MIDDLE && direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
		}
		else if(direction.getAxis() != Direction.Axis.Y || blockhalfs == TripleBlockHalfs.TOP != (direction == Direction.UP) || newState.is(this) && newState.getValue(HALFS) != blockhalfs) 
		{
			return blockhalfs == TripleBlockHalfs.TOP && direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
		}
		else 
		{
			return Blocks.AIR.defaultBlockState();
		}
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos blockpos = context.getClickedPos() ;
		return blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) && blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above().above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
	}
	
	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) 
	{
		world.setBlock(pos.above().above(), this.defaultBlockState().setValue(HALFS, TripleBlockHalfs.TOP), 3);
		world.setBlock(pos.above(), this.defaultBlockState().setValue(HALFS, TripleBlockHalfs.MIDDLE), 3);
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldReader, BlockPos pos) 
	{
		if(state.getValue(HALFS) != TripleBlockHalfs.TOP && state.getValue(HALFS) != TripleBlockHalfs.MIDDLE) 
		{
			return super.canSurvive(state, worldReader, pos);
		}
		else if(state.getValue(HALFS) == TripleBlockHalfs.TOP && state.getValue(HALFS) != TripleBlockHalfs.MIDDLE)
		{
			BlockState blockstate = worldReader.getBlockState(pos.below());
			if(state.getBlock() != this)
			{
				return super.canSurvive(blockstate, worldReader, pos);
			}
			return blockstate.is(this) && blockstate.getValue(HALFS) == TripleBlockHalfs.MIDDLE;
		}
		else
		{
			BlockState blockstate = worldReader.getBlockState(pos.below());
			if(state.getBlock() != this)
			{
				return super.canSurvive(blockstate, worldReader, pos);
			}
			return blockstate.is(this) && blockstate.getValue(HALFS) == TripleBlockHalfs.BOTTOM;
		}
	}
	
	public void placeAt(IWorld world, BlockPos pos, int i) 
	{
		world.setBlock(pos, this.defaultBlockState().setValue(HALFS, TripleBlockHalfs.BOTTOM), i);
		world.setBlock(pos.above(), this.defaultBlockState().setValue(HALFS, TripleBlockHalfs.MIDDLE), i);
		world.setBlock(pos.above().above(), this.defaultBlockState().setValue(HALFS, TripleBlockHalfs.TOP), i);
	}
	
	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity entity) 
	{
		if(!world.isClientSide) 
		{
			if(entity.isCreative()) 
			{
				preventCreativeDropFromBottomPart(world, pos, state, entity);
			} 
			else 
			{
				dropResources(state, world, pos, (TileEntity)null, entity, entity.getMainHandItem());
			}
		}
		
		super.playerWillDestroy(world, pos, state, entity);
	}
	
	@Override
	public void playerDestroy(World world, PlayerEntity entity, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack stack) 
	{
		super.playerDestroy(world, entity, pos, Blocks.AIR.defaultBlockState(), tileEntity, stack);
	}
	
	protected static void preventCreativeDropFromBottomPart(World world, BlockPos pos, BlockState state, PlayerEntity entity) 
	{
		TripleBlockHalfs blockhalfs = state.getValue(HALFS);
		if(blockhalfs == TripleBlockHalfs.TOP) 
		{
			BlockPos blockpos = pos.below();
			BlockState blockstate = world.getBlockState(blockpos);
			if(blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALFS) == TripleBlockHalfs.MIDDLE) 
			{
				world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
				world.levelEvent(entity, 2001, blockpos.below(), Block.getId(blockstate));
			}
			if(blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALFS) == TripleBlockHalfs.BOTTOM) 
			{
				world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
				world.levelEvent(entity, 2001, blockpos.below().below(), Block.getId(blockstate));
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(HALFS);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) 
	{
		return MathHelper.getSeed(pos.getX(), pos.below(state.getValue(HALFS) == TripleBlockHalfs.BOTTOM ? 0 : 1).getY(), pos.getZ());
	}
}
