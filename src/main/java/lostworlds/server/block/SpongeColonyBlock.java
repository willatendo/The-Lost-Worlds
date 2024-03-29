package lostworlds.server.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullSupplier;

public class SpongeColonyBlock extends CoralPlantBlock {
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 7, 16);

	public SpongeColonyBlock(NonNullSupplier<? extends Block> deadBlock, Properties properties) {
		super(deadBlock.get(), properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(WATERLOGGED)) {
			makeParticles(world, pos);
		}
	}

	public static void makeParticles(World world, BlockPos pos) {
		Random random = world.getRandom();
		BasicParticleType basicparticletype = ParticleTypes.BUBBLE_COLUMN_UP;
		world.addAlwaysVisibleParticle(basicparticletype, true, (double) pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + random.nextDouble() + random.nextDouble(), (double) pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
		world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double) pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + 0.4D, (double) pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
	}
}
