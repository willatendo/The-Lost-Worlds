package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/*
 * Author: Willatendo
 * Date: July 12, 2021
 */

public class DeadSpongeColonyBlock extends DeadCoralPlantBlock
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 7, 16);

	public DeadSpongeColonyBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
}
