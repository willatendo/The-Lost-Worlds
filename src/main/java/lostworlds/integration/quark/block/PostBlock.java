package lostworlds.integration.quark.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class PostBlock extends ChainBlock
{
	private static final VoxelShape VERTICAL = Block.box(6, 0, 6, 10, 16, 10);
	private static final VoxelShape HORIZONTAL_NS = Block.box(6, 6, 0, 10, 10, 16);
	private static final VoxelShape HORIZONTAL_EW = Block.box(0, 6, 6, 16, 10, 10);

	public PostBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		switch((Direction.Axis)state.getValue(AXIS)) 
		{
		case X:
		default:
			return HORIZONTAL_EW;
		case Z:
			return HORIZONTAL_NS;
		case Y:
			return VERTICAL;
		}
	}
}
