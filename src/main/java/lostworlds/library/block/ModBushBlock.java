package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class ModBushBlock extends BushBlock implements IGrowable
{
	public ModBushBlock(Properties properties) 
	{
		super(properties);
	}	
	
	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader blockReader, BlockPos pos) 
	{
		Block block = state.getBlock();
		
		return block.is(Blocks.DIRT) || block.is(BlockInit.MOSSY_DIRT) || block.is(BlockInit.PERMIAN_SAND) || block.is(BlockInit.DRIED_SOIL);
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader blockReader, BlockPos pos, BlockState state, boolean bool) 
	{
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		DoublePlantBlock doubleplantblock = (DoublePlantBlock)(this == BlockInit.DICKSONIA ? BlockInit.TALL_DICKSONIA : Blocks.TALL_GRASS);
		if(doubleplantblock.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) 
		{
			doubleplantblock.placeAt(worldIn, pos, 2);
		}
	}
	
	@Override
	public AbstractBlock.OffsetType getOffsetType() 
	{
		return AbstractBlock.OffsetType.XYZ;
	}
}
