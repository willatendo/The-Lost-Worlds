package lostworlds.server.block;

import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class AnalyzerBlock extends MachineBlock {
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 11, 15);

	protected AnalyzerBlock(Properties properties) {
		super(properties);

		this.runCalculation(SHAPE);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return LostWorldsBlockEntities.ANALYZER_TILE_ENTITY.create();
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(ON)) {
			return 15;
		} else {
			return 1;
		}
	}
}
