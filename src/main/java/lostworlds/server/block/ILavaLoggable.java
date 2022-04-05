package lostworlds.server.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public interface ILavaLoggable extends IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

	@Override
	default boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid) {
		return !state.getValue(BooleanProperty.create("lavalogged")) && fluid == Fluids.LAVA;
	}

	@Override
	default boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(LAVALOGGED) && fluid.getType() == Fluids.LAVA) {
			if (!world.isClientSide()) {
				world.setBlock(pos, state.setValue(LAVALOGGED, Boolean.valueOf(true)), 3);
				world.getLiquidTicks().scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(world));
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	default Fluid takeLiquid(IWorld world, BlockPos pos, BlockState state) {
		if (state.getValue(LAVALOGGED)) {
			world.setBlock(pos, state.setValue(LAVALOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}
}
