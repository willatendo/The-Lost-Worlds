package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.content.server.init.PotionInit;
import lostworlds.library.ModDamageSources;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class AshLayerBlock extends Block
{
	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
	protected static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[]{VoxelShapes.empty(), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
	
	public AshLayerBlock() 
	{
		super(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND));
		this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, Integer.valueOf(1)));
	}
	
	@Override
	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType path) 
	{
		switch(path) 
		{
			case LAND:
				return state.getValue(LAYERS) < 5;
			case WATER:
				return false;
			case AIR:
				return false;
			default:
				return false;
		}
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE_BY_LAYER[state.getValue(LAYERS)];
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE_BY_LAYER[state.getValue(LAYERS) - 1];
	}
	
	@Override
	public VoxelShape getBlockSupportShape(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return SHAPE_BY_LAYER[state.getValue(LAYERS)];
	}
	
	@Override
	public VoxelShape getVisualShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE_BY_LAYER[state.getValue(LAYERS)];
	}
	
	@Override
	public boolean useShapeForLightOcclusion(BlockState state) 
	{
		return true;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		if(!blockstate.is(Blocks.ICE) && !blockstate.is(Blocks.PACKED_ICE) && !blockstate.is(Blocks.BARRIER)) 
		{
			if(!blockstate.is(Blocks.HONEY_BLOCK) && !blockstate.is(Blocks.SOUL_SAND)) 
			{
				return Block.isFaceFull(blockstate.getCollisionShape(reader, pos.below()), Direction.UP) || blockstate.getBlock() == this && blockstate.getValue(LAYERS) == 8;
			} 
			else 
			{
				return true;
			}
		} 
		else 
		{
			return false;
		}
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}
	
	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext context) 
	{
		int i = state.getValue(LAYERS);
		if(context.getItemInHand().getItem() == this.asItem() && i < 8) 
		{
			if(context.replacingClickedOnBlock()) 
			{
				return context.getClickedFace() == Direction.UP;
			} 
			else 
			{
				return true;
			}
		} 
		else 
		{
			return i == 1;
		}
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if(blockstate.is(this)) 
		{
			int i = blockstate.getValue(LAYERS);
			return blockstate.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
		} 
		else 
		{
			return super.getStateForPlacement(context);
		}
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(LAYERS);
	}
	
	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) 
	{
		super.entityInside(state, world, pos, entity);
		if(world instanceof ServerWorld && entity instanceof BoatEntity) 
		{
			world.destroyBlock(new BlockPos(pos), true, entity);
		}
		
		if(!world.isClientSide) 
		{
			if(entity instanceof LivingEntity) 
			{
				LivingEntity livingentity = (LivingEntity)entity;
				if(!livingentity.isInvulnerableTo(ModDamageSources.ASHY_LUNG)) 
				{
					livingentity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}
}
