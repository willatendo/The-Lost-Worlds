package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CultivatorBlock extends MachineBlock {
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 15);

	public CultivatorBlock(Properties properties) {
		super(properties);

		this.runCalculation(SHAPE);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return LostWorldsBlockEntities.CULTIVATOR_TILE_ENTITY.create();
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(ON)) {
			return 15;
		} else {
			return 1;
		}
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(ON)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) {
				world.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}
		}
	}
}
