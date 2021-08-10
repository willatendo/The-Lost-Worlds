package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/*
 * Author: Willatendo
 * Date: July 12, 2021
 */

public class SpongeColonyBlock extends CoralPlantBlock
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 7, 16);

	public SpongeColonyBlock(Properties properties, Block deadBlock)
	{
		super(deadBlock, properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
}
