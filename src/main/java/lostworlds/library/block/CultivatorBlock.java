package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.builder.BlockAndItemBuilder;
import lostworlds.library.block.builder.MachineBlockBuilder;
import lostworlds.library.block.entity.CultivatorTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CultivatorBlock extends MachineBlockBuilder
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 15);

	public CultivatorBlock() 
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
		return new CultivatorTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return TileEntityInit.CULTIVATOR_TILE_ENTITY.create();
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
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) 
	{
		if(state.getValue(ON)) 
		{
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY();
			double d2 = (double)pos.getZ() + 0.5D;
			if(rand.nextDouble() < 0.1D) 
			{
				world.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        	}
		}
	}
	
	public static Block create()
	{
		return BlockAndItemBuilder.create("cultivator", new CultivatorBlock());
	}
}
