package lostworlds.server.block;

import lostworlds.server.block.builder.BlockUtils;
import lostworlds.server.item.WaterPlantItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class WaterPlantBlock extends FlowerBlock implements IWaterLoggable {
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public WaterPlantBlock(Effect effect, int lenght, Properties properties) {
		super(effect, lenght, properties);

		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newstate, IWorld world, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, facing, newstate, world, pos, newPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		return reader.getBlockState(pos).getFluidState().getType() == Fluids.WATER && !(reader.getBlockState(pos).getFluidState().getType() == Fluids.WATER);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public static Block create(String id, Block block) {
		Item item = new WaterPlantItem(block);
		BlockUtils.create(id, block, item);
		return block;
	}
}
