package lostworlds.server.block;

import lostworlds.server.block.builder.BlockUtils;
import lostworlds.server.block.builder.MachineBlockBuilder;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
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

public class DNAInjectorBlock extends MachineBlockBuilder {
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 11, 15);

	protected DNAInjectorBlock() {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL));

		runCalculation(SHAPE);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return LostWorldsBlockEntities.DNA_INJECTOR_TILE_ENTITY.create();
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(ON)) {
			return 15;
		} else {
			return 1;
		}
	}

	public static Block create() {
		return BlockUtils.create("dna_injector", new DNAInjectorBlock());
	}
}
