package lostworlds.server.block;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class CalamtiesSuckowiiBlock extends Block implements BonemealableBlock {
	private static final VoxelShape SMALL_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape LARGE_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	private static final VoxelShape COLLISION_SHAPE = Block.box(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
	public static final EnumProperty<BambooLeaves> LEAVES = BlockStateProperties.BAMBOO_LEAVES;
	public static final IntegerProperty STAGE = BlockStateProperties.STAGE;

	public CalamtiesSuckowiiBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(LEAVES, BambooLeaves.NONE).setValue(STAGE, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, LEAVES, STAGE);
	}

	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.XZ;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		VoxelShape voxelshape = state.getValue(LEAVES) == BambooLeaves.LARGE ? LARGE_SHAPE : SMALL_SHAPE;
		Vec3 vector3d = state.getOffset(reader, pos);
		return voxelshape.move(vector3d.x, vector3d.y, vector3d.z);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		Vec3 vector3d = state.getOffset(reader, pos);
		return COLLISION_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		if (!fluidstate.isEmpty()) {
			return null;
		} else {
			BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
			if (blockstate.is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES.tag)) {
				if (blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING.get())) {
					return this.defaultBlockState().setValue(AGE, Integer.valueOf(0));
				} else if (blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get())) {
					int i = blockstate.getValue(AGE) > 0 ? 1 : 0;
					return this.defaultBlockState().setValue(AGE, Integer.valueOf(i));
				} else {
					BlockState blockstate1 = context.getLevel().getBlockState(context.getClickedPos().above());
					return !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()) && !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING.get()) ? LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING.getDefaultState() : this.defaultBlockState().setValue(AGE, blockstate1.getValue(AGE));
				}
			} else {
				return null;
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (!state.canSurvive(world, pos)) {
			world.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(STAGE) == 0;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (state.getValue(STAGE) == 0) {
			if (world.isEmptyBlock(pos.above()) && world.getRawBrightness(pos.above(), 0) >= 9) {
				int i = this.getHeightBelowUpToMax(world, pos) + 1;
				if (i < 16 && ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(3) == 0)) {
					this.growPlant(state, world, pos, rand, i);
					ForgeHooks.onCropsGrowPost(world, pos, state);
				}
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.below()).is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES.tag);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos newPos) {
		if (!state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
		}

		if (direction == Direction.UP && newState.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()) && newState.getValue(AGE) > state.getValue(AGE)) {
			world.setBlock(pos, state.cycle(AGE), 2);
		}

		return super.updateShape(state, direction, newState, world, pos, newPos);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter reader, BlockPos pos, BlockState state, boolean valid) {
		int i = this.getHeightAboveUpToMax(reader, pos);
		int j = this.getHeightBelowUpToMax(reader, pos);
		return i + j + 1 < 16 && reader.getBlockState(pos.above(i)).getValue(STAGE) != 1;
	}

	@Override
	public boolean isBonemealSuccess(Level world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
		int i = this.getHeightAboveUpToMax(world, pos);
		int j = this.getHeightBelowUpToMax(world, pos);
		int k = i + j + 1;
		int l = 1 + rand.nextInt(2);

		for (int i1 = 0; i1 < l; ++i1) {
			BlockPos blockpos = pos.above(i);
			BlockState blockstate = world.getBlockState(blockpos);
			if (k >= 16 || blockstate.getValue(STAGE) == 1 || !world.isEmptyBlock(blockpos.above())) {
				return;
			}

			this.growPlant(blockstate, world, blockpos, rand, k);
			++i;
			++k;
		}
	}

	@Override
	public float getDestroyProgress(BlockState state, Player entity, BlockGetter reader, BlockPos pos) {
		return entity.getMainHandItem().getItem() instanceof SwordItem ? 1.0F : super.getDestroyProgress(state, entity, reader, pos);
	}

	protected void growPlant(BlockState state, Level world, BlockPos pos, Random rand, int age) {
		BlockState blockstate = world.getBlockState(pos.below());
		BlockPos blockpos = pos.below(2);
		BlockState blockstate1 = world.getBlockState(blockpos);
		BambooLeaves bambooleaves = BambooLeaves.NONE;
		if (age >= 1) {
			if (blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
				if (blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
					bambooleaves = BambooLeaves.LARGE;
					if (blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get())) {
						world.setBlock(pos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
						world.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
					}
				}
			} else {
				bambooleaves = BambooLeaves.SMALL;
			}
		}

		int i = state.getValue(AGE) != 1 && !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()) ? 0 : 1;
		int j = (age < 11 || !(rand.nextFloat() < 0.25F)) && age != 15 ? 0 : 1;
		world.setBlock(pos.above(), this.defaultBlockState().setValue(AGE, Integer.valueOf(i)).setValue(LEAVES, bambooleaves).setValue(STAGE, Integer.valueOf(j)), 3);
	}

	protected int getHeightAboveUpToMax(BlockGetter reader, BlockPos pos) {
		int i;
		for (i = 0; i < 16 && reader.getBlockState(pos.above(i + 1)).is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()); ++i) {
		}
		return i;
	}

	protected int getHeightBelowUpToMax(BlockGetter reader, BlockPos pos) {
		int i;
		for (i = 0; i < 16 && reader.getBlockState(pos.below(i + 1)).is(LostWorldsBlocks.CALAMITES_SUCKOWII.get()); ++i) {
		}
		return i;
	}
}
