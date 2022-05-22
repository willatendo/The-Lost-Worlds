package lostworlds.server.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public abstract class ExtendableFlowerBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 8, 10);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
	private static ExtendableStemBlock stem;

	public ExtendableFlowerBlock(Properties properties) {
		super(properties);
		this.stem = stem();
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}

	public abstract ExtendableStemBlock stem();

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (!state.canSurvive(world, pos)) {
			world.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < 2;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
		BlockPos blockpos = pos.above();
		if (level.isEmptyBlock(blockpos) && blockpos.getY() < 256) {
			int i = state.getValue(AGE);
			if (i < 2 && ForgeHooks.onCropsGrowPre(level, blockpos, state, true)) {
				boolean flag = false;
				boolean flag1 = false;
				BlockState blockstate = level.getBlockState(pos.below());
				Block block = blockstate.getBlock();
				if (blockstate.is(BlockTags.DIRT)) {
					flag = true;
				} else if (block == this.stem()) {
					int j = 1;

					for (int k = 0; k < 4; ++k) {
						Block block1 = level.getBlockState(pos.below(j + 1)).getBlock();
						if (block1 != this.stem()) {
							if (block1.defaultBlockState().is(BlockTags.DIRT)) {
								flag1 = true;
							}
							break;
						}

						++j;
					}

					if (j < 2 || j <= rand.nextInt(flag1 ? 5 : 4)) {
						flag = true;
					}
				} else if (blockstate.isAir()) {
					flag = true;
				}

				if (flag && allNeighborsEmpty(level, blockpos, (Direction) null) && level.isEmptyBlock(pos.above(2))) {
					level.setBlock(pos, this.stem().getStateForPlacement(level, pos), 2);
					this.placeGrownFlower(level, blockpos, i);
				} else if (i < 4) {
					int l = rand.nextInt(4);
					if (flag1) {
						++l;
					}

					boolean flag2 = false;

					for (int i1 = 0; i1 < l; ++i1) {
						Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
						BlockPos blockpos1 = pos.relative(direction);
						if (level.isEmptyBlock(blockpos1) && level.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(level, blockpos1, direction.getOpposite())) {
							this.placeGrownFlower(level, blockpos1, i + 1);
							flag2 = true;
						}
					}

					if (flag2) {
						level.setBlock(pos, this.stem().getStateForPlacement(level, pos), 2);
					} else {
						this.placeDeadFlower(level, pos);
					}
				} else {
					this.placeDeadFlower(level, pos);
				}
				ForgeHooks.onCropsGrowPost(level, pos, state);
			}
		}
	}

	private void placeGrownFlower(Level world, BlockPos pos, int age) {
		world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(age)), 2);
		world.levelEvent(1033, pos, 0);
	}

	private void placeDeadFlower(Level world, BlockPos pos) {
		world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(2)), 2);
		world.levelEvent(1034, pos, 0);
	}

	private static boolean allNeighborsEmpty(LevelReader reader, BlockPos pos, @Nullable Direction direction) {
		for (Direction directions : Direction.Plane.HORIZONTAL) {
			if (directions != direction && !reader.isEmptyBlock(pos.relative(directions))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, LevelAccessor world, BlockPos pos, BlockPos newpos) {
		if (direction != Direction.UP && !state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, newstate, world, pos, newpos);
	}

	@Override
	public boolean canSurvive(BlockState sate, LevelReader reader, BlockPos pos) {
		BlockState blockstate = reader.getBlockState(pos.below());
		if (blockstate.getBlock() != this.stem() && !blockstate.is(BlockTags.DIRT)) {
			if (!blockstate.isAir()) {
				return false;
			} else {
				boolean flag = false;

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockState blockstate1 = reader.getBlockState(pos.relative(direction));
					if (blockstate1.is(this.stem())) {
						if (flag) {
							return false;
						}

						flag = true;
					} else if (!blockstate1.isAir()) {
						return false;
					}
				}

				return flag;
			}
		} else {
			return true;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public static void generatePlant(LevelAccessor world, BlockPos pos, Random rand, int maxHeight) {
		world.setBlock(pos, ((ExtendableStemBlock) stem).getStateForPlacement(world, pos), 2);
		growTreeRecursive(world, pos, rand, pos, maxHeight, 0);
	}

	private static void growTreeRecursive(LevelAccessor world, BlockPos pos, Random rand, BlockPos newpos, int maxHeight, int greater) {
		ExtendableStemBlock block = (ExtendableStemBlock) stem;
		int i = rand.nextInt(4) + 1;
		if (greater == 0) {
			++i;
		}

		for (int j = 0; j < i; ++j) {
			BlockPos blockpos = pos.above(j + 1);
			if (!allNeighborsEmpty(world, blockpos, (Direction) null)) {
				return;
			}

			world.setBlock(blockpos, block.getStateForPlacement(world, blockpos), 2);
			world.setBlock(blockpos.below(), block.getStateForPlacement(world, blockpos.below()), 2);
		}

		boolean flag = false;
		if (greater < 4) {
			int l = rand.nextInt(4);
			if (greater == 0) {
				++l;
			}

			for (int k = 0; k < l; ++k) {
				Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
				BlockPos blockpos1 = pos.above(i).relative(direction);
				if (Math.abs(blockpos1.getX() - newpos.getX()) < maxHeight && Math.abs(blockpos1.getZ() - newpos.getZ()) < maxHeight && world.isEmptyBlock(blockpos1) && world.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite())) {
					flag = true;
					world.setBlock(blockpos1, block.getStateForPlacement(world, blockpos1), 2);
					world.setBlock(blockpos1.relative(direction.getOpposite()), block.getStateForPlacement(world, blockpos1.relative(direction.getOpposite())), 2);
					growTreeRecursive(world, blockpos1, rand, newpos, maxHeight, greater + 1);
				}
			}
		}

		if (!flag) {
			world.setBlock(pos.above(i), stem.flower().defaultBlockState().setValue(AGE, Integer.valueOf(2)), 2);
		}

	}

	@Override
	public void onProjectileHit(Level world, BlockState state, BlockHitResult result, Projectile entity) {
		if (entity.getType().is(EntityTypeTags.IMPACT_PROJECTILES)) {
			BlockPos blockpos = result.getBlockPos();
			world.destroyBlock(blockpos, true, entity);
		}

	}
}
