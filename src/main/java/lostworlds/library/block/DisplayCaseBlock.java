package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import net.minecraft.block.Block;
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
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DisplayCaseBlock extends Block implements ITileEntityProvider
{
	private static final EnumProperty<DisplayCaseSize> SIZE = EnumProperty.create("size", DisplayCaseSize.class);
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public DisplayCaseBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(SIZE, DisplayCaseSize.SINGLE).setValue(HORIZONTAL_FACING, Direction.NORTH));
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
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		boolean north = state.getValue(HORIZONTAL_FACING) == Direction.NORTH;
		boolean south = state.getValue(HORIZONTAL_FACING) == Direction.SOUTH;
		boolean east = state.getValue(HORIZONTAL_FACING) == Direction.EAST;
		boolean west = state.getValue(HORIZONTAL_FACING) == Direction.WEST;
		if(north)
		{
			boolean right = world.getBlockState(pos.east()).getBlock() == this;
			boolean left = world.getBlockState(pos.west()).getBlock() == this;
			if(!right && !left)
			{
				return state.setValue(SIZE, DisplayCaseSize.SINGLE);
			}
			else if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(south)
		{
			boolean right = world.getBlockState(pos.west()).getBlock() == this;
			boolean left = world.getBlockState(pos.east()).getBlock() == this;
			if(!right && !left)
			{
				return state.setValue(SIZE, DisplayCaseSize.SINGLE);
			}
			else if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(east)
		{
			boolean right = world.getBlockState(pos.south()).getBlock() == this;
			boolean left = world.getBlockState(pos.north()).getBlock() == this;
			if(!right && !left)
			{
				return state.setValue(SIZE, DisplayCaseSize.SINGLE);
			}
			else if(right && left)
			{
				return 	state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		else if(west)
		{
			boolean right = world.getBlockState(pos.north()).getBlock() == this;
			boolean left = world.getBlockState(pos.south()).getBlock() == this;
			if(!right && !left)
			{
				return state.setValue(SIZE, DisplayCaseSize.SINGLE);
			}
			else if(right && left)
			{
				return state.setValue(SIZE, DisplayCaseSize.BOTH);
			}
			else if(right)
			{
				return state.setValue(SIZE, DisplayCaseSize.RIGHT);
			}
			else if(left)
			{
				return state.setValue(SIZE, DisplayCaseSize.LEFT);
			}
		}
		
		return super.updateShape(state, direction, newState, world, pos, newPos);
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
			if(tile instanceof DisplayCaseTileEntity) 
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
			if(tileentity instanceof DisplayCaseTileEntity) 
			{
				((DisplayCaseTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
		}
	}
	
	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean b) 
	{
		if(!state.is(newState.getBlock())) 
		{
			TileEntity tileentity = world.getBlockEntity(pos);
			if(tileentity instanceof DisplayCaseTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (DisplayCaseTileEntity)tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			
			super.onRemove(state, world, pos, newState, b);
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
	public TileEntity newBlockEntity(IBlockReader reader) 
	{
		return new DisplayCaseTileEntity();
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return TileEntityInit.DISPLAY_CASE_TILE_ENTITY.create();
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(SIZE, HORIZONTAL_FACING);
	}
}
