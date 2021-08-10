package lostworlds.library.block.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.content.server.init.SoundInit;
import lostworlds.library.ModBlockStateProperties;
import lostworlds.library.tileentity.AnalyserTileEntity;
import lostworlds.library.tileentity.DNAExtractorTileEntity;
import lostworlds.library.tileentity.DNAInjectorTileEntity;
import lostworlds.library.tileentity.FossilCleanerTileEntity;
import lostworlds.library.tileentity.FossilGrinderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public abstract class BaseMachineBlock extends Block implements ITileEntityProvider
{
	protected static final Map<Block, Map<Direction, VoxelShape>> SHAPES = new HashMap<Block, Map<Direction, VoxelShape>>();
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty ON = ModBlockStateProperties.ON;
	
	public BaseMachineBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ON, Boolean.valueOf(false)).setValue(HORIZONTAL_FACING, Direction.NORTH));
	}	
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) 
	{
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	protected static VoxelShape calculateShapes(Direction to, VoxelShape shape) 
	{
		VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };

		int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) 
		{
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = VoxelShapes.empty();
		}

		return buffer[0];
	}

	protected void runCalculation(VoxelShape shape) 
	{
		SHAPES.put(this, new HashMap<Direction, VoxelShape>());
		Map<Direction, VoxelShape> facingMap = SHAPES.get(this);
		for (Direction direction : Direction.values()) 
		{
			facingMap.put(direction, calculateShapes(direction, shape));
		}
	}	
	
	@Override
	public boolean triggerEvent(BlockState state, World world, BlockPos pos, int i1, int i2) 
	{
		super.triggerEvent(state, world, pos, i1, i2);
		TileEntity tileentity = world.getBlockEntity(pos);
		return tileentity == null ? false : tileentity.triggerEvent(i1, i2);
	}
	
	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) 
	{
		TileEntity tileentity = world.getBlockEntity(pos);
		return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider)tileentity : null;
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) 
	{
		if(!world.isClientSide) 
		{
			TileEntity tile = world.getBlockEntity(pos);
			if(tile instanceof FossilCleanerTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)tile, pos);
				return ActionResultType.SUCCESS;
			}
			else if(tile instanceof FossilGrinderTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)tile, pos);
				return ActionResultType.SUCCESS;
			}
			else if(tile instanceof DNAExtractorTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)tile, pos);
				return ActionResultType.SUCCESS;
			}
			else if(tile instanceof AnalyserTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)tile, pos);
				return ActionResultType.SUCCESS;
			}
			else if(tile instanceof DNAInjectorTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}
		
	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) 
	{
		if(stack.hasCustomHoverName()) 
		{
			TileEntity tileentity = world.getBlockEntity(pos);
			if(tileentity instanceof FossilCleanerTileEntity) 
			{
				((FossilCleanerTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
			else if(tileentity instanceof FossilGrinderTileEntity) 
			{
				((FossilGrinderTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
			else if(tileentity instanceof DNAExtractorTileEntity) 
			{
				((DNAExtractorTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
			else if(tileentity instanceof AnalyserTileEntity) 
			{
				((AnalyserTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
			else if(tileentity instanceof DNAInjectorTileEntity) 
			{
				((DNAInjectorTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
		}
	}
	
	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean b) 
	{
		if(!state.is(newState.getBlock())) 
		{
			TileEntity tileentity = world.getBlockEntity(pos);
			if(tileentity instanceof FossilCleanerTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (FossilCleanerTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			else if(tileentity instanceof FossilGrinderTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (FossilGrinderTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			else if(tileentity instanceof DNAExtractorTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (DNAExtractorTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			else if(tileentity instanceof AnalyserTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (AnalyserTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			else if(tileentity instanceof DNAInjectorTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (DNAInjectorTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			
			super.onRemove(state, world, pos, newState, b);
		}
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) 
	{
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY();
		double d2 = (double)pos.getZ() + 0.5D;
		if(rand.nextDouble() < 0.1D) 
		{
			world.playLocalSound(d0, d1, d2, SoundInit.MACHINE_WHIRLING, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
         }
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) 
	{
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState state, World world, BlockPos pos) 
	{
		return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}
	
	@Override
	public BlockRenderType getRenderShape(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(HORIZONTAL_FACING, ON);
	}
}
