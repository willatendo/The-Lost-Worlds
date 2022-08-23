package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.block.properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GeyserBlock extends Block implements ILavaLoggable {
	public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 16, 14);
	public static final BooleanProperty LAVALOGGED = ModBlockStateProperties.LAVALOGGED;
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	public GeyserBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos newPos, boolean b) {
		if (!world.isClientSide) {
			boolean flag = state.getValue(LIT);
			if (flag != world.hasNeighborSignal(pos)) {
				if (flag) {
					world.getBlockTicks().scheduleTick(pos, this, 4);
				} else {
					world.setBlock(pos, state.cycle(LIT), 2);
				}
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (state.getValue(LIT) && !world.hasNeighborSignal(pos)) {
			world.setBlock(pos, state.cycle(LIT), 2);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.LAVA;
		return super.getStateForPlacement(context).setValue(LAVALOGGED, Boolean.valueOf(flag)).setValue(LIT, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		BlockState blockstate = reader.getBlockState(pos.below());
		return blockstate.getBlock() == Blocks.AIR ? false : blockstate.getBlock() == Blocks.LAVA ? false : blockstate.getBlock() == Blocks.MAGMA_BLOCK ? false : true;
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LAVALOGGED, LIT);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) {
		if (state.getValue(LAVALOGGED)) {
			world.getLiquidTicks().scheduleTick(pos, Fluids.LAVA, Fluids.LAVA.getTickDelay(world));
		}

		return Direction.DOWN == direction && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(LAVALOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(state);
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(LAVALOGGED)) {
			return 15;
		} else {
			return 2;
		}
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextInt(10) == 0) {
			world.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
		}

		if (rand.nextInt(5) == 0) {
			for (int i = 0; i < rand.nextInt(1) + 1; ++i) {
				world.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, (double) (rand.nextFloat() / 2.0F), 5.0E-5D, (double) (rand.nextFloat() / 2.0F));
			}
		}

		if (state.getValue(LIT) == true) {
			makeParticles(world, pos);
		}
	}

	public static void makeParticles(World world, BlockPos pos) {
		Random random = world.getRandom();
		BasicParticleType basicparticletype = ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
		world.addAlwaysVisibleParticle(basicparticletype, true, (double) pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + random.nextDouble() + random.nextDouble(), (double) pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + 0.4D, (double) pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
	}
}
