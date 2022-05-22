package lostworlds.server.block;

import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class AnalyzerBlock extends MachineBlock {
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 11, 15);

	protected AnalyzerBlock(Properties properties) {
		super(properties);

		this.runCalculation(SHAPE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
		return LostWorldsBlockEntities.ANALYZER_TILE_ENTITY.create();
	}

	@Override
	public int getLightValue(BlockState state, BlockGetter world, BlockPos pos) {
		if (state.getValue(ON)) {
			return 15;
		} else {
			return 1;
		}
	}
}
