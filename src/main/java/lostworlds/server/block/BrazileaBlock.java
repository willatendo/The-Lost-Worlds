package lostworlds.server.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

public class BrazileaBlock extends BushBlock {
	private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);

	public BrazileaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		super.entityInside(state, world, pos, entity);
		if (world instanceof ServerWorld && entity instanceof BoatEntity) {
			world.destroyBlock(new BlockPos(pos), true, entity);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
		FluidState fluidstate = reader.getFluidState(pos);
		return (fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.WATER;
	}
}
