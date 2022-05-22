package lostworlds.server.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;

public abstract class ExtendableStemBlock extends PipeBlock {
	public ExtendableStemBlock(Properties properties) {
		super(0.3125F, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
	}

	public abstract Block flower();

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
	}

	public BlockState getStateForPlacement(BlockGetter reader, BlockPos pos) {
		Block block = reader.getBlockState(pos.below()).getBlock();
		Block block1 = reader.getBlockState(pos.above()).getBlock();
		Block block2 = reader.getBlockState(pos.north()).getBlock();
		Block block3 = reader.getBlockState(pos.east()).getBlock();
		Block block4 = reader.getBlockState(pos.south()).getBlock();
		Block block5 = reader.getBlockState(pos.west()).getBlock();
		return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(block == this || block == this.flower() || block.defaultBlockState().is(BlockTags.DIRT))).setValue(UP, Boolean.valueOf(block1 == this || block1 == this.flower())).setValue(NORTH, Boolean.valueOf(block2 == this || block2 == this.flower())).setValue(EAST, Boolean.valueOf(block3 == this || block3 == this.flower())).setValue(SOUTH, Boolean.valueOf(block4 == this || block4 == this.flower())).setValue(WEST, Boolean.valueOf(block5 == this || block5 == this.flower()));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, LevelAccessor world, BlockPos pos, BlockPos newpos) {
		if (!state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
			return super.updateShape(state, direction, newstate, world, pos, newpos);
		} else {
			boolean flag = newstate.getBlock() == this || newstate.is(this.flower()) || direction == Direction.DOWN && newstate.is(BlockTags.DIRT);
			return state.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (!state.canSurvive(world, pos)) {
			world.destroyBlock(pos, true);
		}

	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		BlockState blockstate = reader.getBlockState(pos.below());
		boolean flag = !reader.getBlockState(pos.above()).isAir() && !blockstate.isAir();

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			BlockPos blockpos = pos.relative(direction);
			Block block = reader.getBlockState(blockpos).getBlock();
			if (block == this) {
				if (flag) {
					return false;
				}

				Block block1 = reader.getBlockState(blockpos.below()).getBlock();
				if (block1 == this || block1.defaultBlockState().is(BlockTags.DIRT)) {
					return true;
				}
			}
		}

		Block block2 = blockstate.getBlock();
		return block2 == this || block2.defaultBlockState().is(BlockTags.DIRT);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
		return false;
	}
}
