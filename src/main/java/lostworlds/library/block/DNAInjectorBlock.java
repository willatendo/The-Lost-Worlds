package lostworlds.library.block;

import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.builder.BlockAndItemBuilder;
import lostworlds.library.block.builder.MachineBlockBuilder;
import lostworlds.library.tileentity.DNAInjectorTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class DNAInjectorBlock extends MachineBlockBuilder
{
	public static final VoxelShape SHAPE = Block.box(1, 0, 3, 15, 9, 16);
	
	protected DNAInjectorBlock() 
	{
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL));
		
		runCalculation(SHAPE);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) 
	{
		return new DNAInjectorTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return TileEntityInit.DNA_INJECTOR_TILE_ENTITY.get().create();
	}
	
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) 
	{
		if(state.getValue(ON))
		{
			return 15;
		}
		else
		{
			return 1;
		}
	}
	
	public static Block create()
	{
		return BlockAndItemBuilder.create("dna_injector", new DNAInjectorBlock());
	}
}
