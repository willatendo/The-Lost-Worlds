package lostworlds.integration.quark.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class QuarkBookshelfBlock extends Block 
{
	public QuarkBookshelfBlock(Properties properties) 
	{
		super(properties);
	}

	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) 
	{
		return true;
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) 
	{
		return 1;
	}
}
