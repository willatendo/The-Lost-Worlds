package lostworlds.server.block;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

public class CalamtiesSuckowiiBlock extends Block implements IGrowable
{
	private static final VoxelShape SMALL_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape LARGE_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	private static final VoxelShape COLLISION_SHAPE = Block.box(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
	public static final EnumProperty<BambooLeaves> LEAVES = BlockStateProperties.BAMBOO_LEAVES;
	public static final IntegerProperty STAGE = BlockStateProperties.STAGE;

	public CalamtiesSuckowiiBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(LEAVES, BambooLeaves.NONE).setValue(STAGE, Integer.valueOf(0)));
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(AGE, LEAVES, STAGE);
	}
	
	@Override
	public AbstractBlock.OffsetType getOffsetType() 
	{
		return AbstractBlock.OffsetType.XZ;
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return true;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		VoxelShape voxelshape = state.getValue(LEAVES) == BambooLeaves.LARGE ? LARGE_SHAPE : SMALL_SHAPE;
		Vector3d vector3d = state.getOffset(reader, pos);
		return voxelshape.move(vector3d.x, vector3d.y, vector3d.z);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType type) 
	{
		return false;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		Vector3d vector3d = state.getOffset(reader, pos);
		return COLLISION_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		if(!fluidstate.isEmpty()) 
		{
			return null;
		}
		else 
		{
			BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
			if(blockstate.is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES)) 
			{
				if(blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING)) 
				{
					return this.defaultBlockState().setValue(AGE, Integer.valueOf(0));
				} 
				else if(blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII)) 
				{
					int i = blockstate.getValue(AGE) > 0 ? 1 : 0;
					return this.defaultBlockState().setValue(AGE, Integer.valueOf(i));
				} 
				else 
				{
					BlockState blockstate1 = context.getLevel().getBlockState(context.getClickedPos().above());
					return !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII) && !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING) ? LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING.defaultBlockState() : this.defaultBlockState().setValue(AGE, blockstate1.getValue(AGE));
				}
			} 
			else 
			{
				return null;
			}
		}
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
		return state.getValue(STAGE) == 0;
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
	{
		if(state.getValue(STAGE) == 0) 
		{
			if(world.isEmptyBlock(pos.above()) && world.getRawBrightness(pos.above(), 0) >= 9) 
			{
				int i = this.getHeightBelowUpToMax(world, pos) + 1;
				if(i < 16 && ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(3) == 0)) 
				{
					this.growPlant(state, world, pos, rand, i);
					ForgeHooks.onCropsGrowPost(world, pos, state);
				}
			}
		}
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		return reader.getBlockState(pos.below()).is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES);
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		if(!state.canSurvive(world, pos)) 
		{
			world.getBlockTicks().scheduleTick(pos, this, 1);
		}
		
		if(direction == Direction.UP && newState.is(LostWorldsBlocks.CALAMITES_SUCKOWII) && newState.getValue(AGE) > state.getValue(AGE)) 
		{
			world.setBlock(pos, state.cycle(AGE), 2);
		}
		
		return super.updateShape(state, direction, newState, world, pos, newPos);
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean valid) 
	{
		int i = this.getHeightAboveUpToMax(reader, pos);
		int j = this.getHeightBelowUpToMax(reader, pos);
		return i + j + 1 < 16 && reader.getBlockState(pos.above(i)).getValue(STAGE) != 1;
	}
	
	@Override
	public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}
	
	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) 
	{
		int i = this.getHeightAboveUpToMax(world, pos);
		int j = this.getHeightBelowUpToMax(world, pos);
		int k = i + j + 1;
		int l = 1 + rand.nextInt(2);
		
		for(int i1 = 0; i1 < l; ++i1) 
		{
			BlockPos blockpos = pos.above(i);
			BlockState blockstate = world.getBlockState(blockpos);
			if(k >= 16 || blockstate.getValue(STAGE) == 1 || !world.isEmptyBlock(blockpos.above())) 
			{
				return;
			}
			
			this.growPlant(blockstate, world, blockpos, rand, k);
			++i;
			++k;
		}
	}
	
	@Override
	public float getDestroyProgress(BlockState state, PlayerEntity entity, IBlockReader reader, BlockPos pos) 
	{
		return entity.getMainHandItem().getItem() instanceof SwordItem ? 1.0F : super.getDestroyProgress(state, entity, reader, pos);
	}
	
	protected void growPlant(BlockState state, World world, BlockPos pos, Random rand, int age) 
	{
		BlockState blockstate = world.getBlockState(pos.below());
		BlockPos blockpos = pos.below(2);
		BlockState blockstate1 = world.getBlockState(blockpos);
		BambooLeaves bambooleaves = BambooLeaves.NONE;
		if(age >= 1) 
		{
			if(blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) 
			{
				if(blockstate.is(LostWorldsBlocks.CALAMITES_SUCKOWII) && blockstate.getValue(LEAVES) != BambooLeaves.NONE)
				{
					bambooleaves = BambooLeaves.LARGE;
					if(blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII)) 
					{
						world.setBlock(pos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
						world.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
					}
				}
			} 
			else 
			{
				bambooleaves = BambooLeaves.SMALL;
			}
		}
		
		int i = state.getValue(AGE) != 1 && !blockstate1.is(LostWorldsBlocks.CALAMITES_SUCKOWII) ? 0 : 1;
		int j = (age < 11 || !(rand.nextFloat() < 0.25F)) && age != 15 ? 0 : 1;
		world.setBlock(pos.above(), this.defaultBlockState().setValue(AGE, Integer.valueOf(i)).setValue(LEAVES, bambooleaves).setValue(STAGE, Integer.valueOf(j)), 3);
	}
	
	protected int getHeightAboveUpToMax(IBlockReader reader, BlockPos pos) 
	{
		int i;
		for(i = 0; i < 16 && reader.getBlockState(pos.above(i + 1)).is(LostWorldsBlocks.CALAMITES_SUCKOWII); ++i) { }
		return i;
	}
	
	protected int getHeightBelowUpToMax(IBlockReader reader, BlockPos pos) 
	{
		int i;
		for(i = 0; i < 16 && reader.getBlockState(pos.below(i + 1)).is(LostWorldsBlocks.CALAMITES_SUCKOWII); ++i) { }
		return i;
	}
}
