package lostworlds.server.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class GroundBlock extends Block {
	public GroundBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) {
		return Direction.DOWN == direction && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		BlockState blockstate = reader.getBlockState(pos.below());
		return blockstate.is(Blocks.AIR) ? false : blockstate.is(Blocks.WATER) ? false : blockstate.is(Blocks.LAVA) ? false : true;
	}
}
