package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import tyrannotitanlib.library.base.block.TyrannoBushBlock;

public class DicksoniaBlock extends TyrannoBushBlock {
	private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 8, 10);

	public DicksoniaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
