package lostworlds.library.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;

public abstract class ExtendableFlowerBlock extends Block 
{
	private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 8, 10);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
	private static ExtendableStemBlock stem;
	
	public ExtendableFlowerBlock(Properties properties) 
	{
		super(properties);
		this.stem = stem();
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}
	
	public abstract ExtendableStemBlock stem();
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
	{
		if(!state.canSurvive(world, pos)) 
		{
			world.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) 
	{
		return state.getValue(AGE) < 2;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
	{
		BlockPos blockpos = pos.above();
		if(world.isEmptyBlock(blockpos) && blockpos.getY() < 256) 
		{
			int i = state.getValue(AGE);
			if (i < 2 && ForgeHooks.onCropsGrowPre(world, blockpos, state, true)) 
			{
				boolean flag = false;
				boolean flag1 = false;
				BlockState blockstate = world.getBlockState(pos.below());
				Block block = blockstate.getBlock();
				if(block.is(Tags.Blocks.DIRT)) 
				{
					flag = true;
				} 
				else if(block == this.stem()) 
				{
					int j = 1;

					for(int k = 0; k < 4; ++k) 
					{
						Block block1 = world.getBlockState(pos.below(j + 1)).getBlock();
						if(block1 != this.stem()) 
						{
							if(block1.is(Tags.Blocks.DIRT)) 
							{
								flag1 = true;
							}
							break;
						}

						++j;
					}

					if(j < 2 || j <= rand.nextInt(flag1 ? 5 : 4)) 
					{
						flag = true;
					}
				} 
				else if(blockstate.isAir(world, pos.below())) 
				{
					flag = true;
				}

				if(flag && allNeighborsEmpty(world, blockpos, (Direction) null) && world.isEmptyBlock(pos.above(2)))
				{
					world.setBlock(pos, this.stem().getStateForPlacement(world, pos), 2);
					this.placeGrownFlower(world, blockpos, i);
				} 
				else if(i < 4) 
				{
					int l = rand.nextInt(4);
					if(flag1) 
					{
						++l;
					}

					boolean flag2 = false;

					for(int i1 = 0; i1 < l; ++i1) 
					{
						Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
						BlockPos blockpos1 = pos.relative(direction);
						if(world.isEmptyBlock(blockpos1) && world.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite())) 
						{
							this.placeGrownFlower(world, blockpos1, i + 1);
							flag2 = true;
						}
					}

					if(flag2) 
					{
						world.setBlock(pos, this.stem().getStateForPlacement(world, pos), 2);
					} 
					else 
					{
						this.placeDeadFlower(world, pos);
					}
				} 
				else 
				{
					this.placeDeadFlower(world, pos);
				}
				ForgeHooks.onCropsGrowPost(world, pos, state);
			}
		}
	}

	private void placeGrownFlower(World world, BlockPos pos, int age) 
	{
		world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(age)), 2);
		world.levelEvent(1033, pos, 0);
	}

	private void placeDeadFlower(World world, BlockPos pos) 
	{
		world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(2)), 2);
		world.levelEvent(1034, pos, 0);
	}

	private static boolean allNeighborsEmpty(IWorldReader reader, BlockPos pos, @Nullable Direction direction) 
	{
		for(Direction directions : Direction.Plane.HORIZONTAL) 
		{
			if(directions != direction && !reader.isEmptyBlock(pos.relative(directions))) 
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, IWorld world, BlockPos pos, BlockPos newpos) 
	{
		if(direction != Direction.UP && !state.canSurvive(world, pos)) 
		{
			world.getBlockTicks().scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, newstate, world, pos, newpos);
	}

	@Override
	public boolean canSurvive(BlockState sate, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		if(blockstate.getBlock() != this.stem() && !blockstate.is(Tags.Blocks.DIRT)) 
		{
			if(!blockstate.isAir(reader, pos.below())) 
			{
				return false;
			} 
			else 
			{
				boolean flag = false;

				for(Direction direction : Direction.Plane.HORIZONTAL) 
				{
					BlockState blockstate1 = reader.getBlockState(pos.relative(direction));
					if(blockstate1.is(this.stem())) 
					{
						if(flag) 
						{
							return false;
						}

						flag = true;
					} 
					else if(!blockstate1.isAir(reader, pos.relative(direction))) 
					{
						return false;
					}
				}

				return flag;
			}
		} 
		else 
		{
			return true;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(AGE);
	}

	public static void generatePlant(IWorld world, BlockPos pos, Random rand, int maxHeight) 
	{
		world.setBlock(pos, ((ExtendableStemBlock) stem).getStateForPlacement(world, pos), 2);
		growTreeRecursive(world, pos, rand, pos, maxHeight, 0);
	}

	private static void growTreeRecursive(IWorld world, BlockPos pos, Random rand, BlockPos newpos, int maxHeight, int greater) 
	{
		ExtendableStemBlock block = (ExtendableStemBlock) stem;
		int i = rand.nextInt(4) + 1;
		if(greater == 0) 
		{
			++i;
		}

		for(int j = 0; j < i; ++j) 
		{
			BlockPos blockpos = pos.above(j + 1);
			if(!allNeighborsEmpty(world, blockpos, (Direction) null)) 
			{
				return;
			}

			world.setBlock(blockpos, block.getStateForPlacement(world, blockpos), 2);
			world.setBlock(blockpos.below(), block.getStateForPlacement(world, blockpos.below()), 2);
		}

		boolean flag = false;
		if(greater < 4) 
		{
			int l = rand.nextInt(4);
			if(greater == 0) 
			{
				++l;
			}

			for(int k = 0; k < l; ++k) 
			{
				Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
				BlockPos blockpos1 = pos.above(i).relative(direction);
				if(Math.abs(blockpos1.getX() - newpos.getX()) < maxHeight && Math.abs(blockpos1.getZ() - newpos.getZ()) < maxHeight && world.isEmptyBlock(blockpos1) && world.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite())) 
				{
					flag = true;
					world.setBlock(blockpos1, block.getStateForPlacement(world, blockpos1), 2);
					world.setBlock(blockpos1.relative(direction.getOpposite()), block.getStateForPlacement(world, blockpos1.relative(direction.getOpposite())), 2);
					growTreeRecursive(world, blockpos1, rand, newpos, maxHeight, greater + 1);
				}
			}
		}

		if(!flag) 
		{
			world.setBlock(pos.above(i), stem.flower().defaultBlockState().setValue(AGE, Integer.valueOf(2)), 2);
		}

	}

	@Override
	public void onProjectileHit(World world, BlockState state, BlockRayTraceResult result, ProjectileEntity entity) 
	{
		if(entity.getType().is(EntityTypeTags.IMPACT_PROJECTILES)) 
		{
			BlockPos blockpos = result.getBlockPos();
			world.destroyBlock(blockpos, true, entity);
		}

	}
}
