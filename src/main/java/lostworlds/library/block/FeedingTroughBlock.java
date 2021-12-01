package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.entity.FeedingTroughTileEntity;
import lostworlds.library.entity.CreatureDiet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class FeedingTroughBlock extends Block 
{
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
	public static final EnumProperty<CreatureDiet> DIET = EnumProperty.create("diet", CreatureDiet.class);
		
	public FeedingTroughBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIET, CreatureDiet.NONE));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(DIET);
		super.createBlockStateDefinition(builder);
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
			if(tile instanceof FeedingTroughTileEntity) 
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
			if(tileentity instanceof FeedingTroughTileEntity) 
			{
				((FeedingTroughTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
		}
	}
	
	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean b) 
	{
		if(!state.is(newState.getBlock())) 
		{
			TileEntity tileentity = world.getBlockEntity(pos);
			if(tileentity instanceof FeedingTroughTileEntity) 
			{
				InventoryHelper.dropContents(world, pos, (FeedingTroughTileEntity)tileentity);
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
	public float getShadeBrightness(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return 1.0F;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState state, World world, BlockPos pos) 
	{
		return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}

	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return TileEntityInit.FEEDING_TROUGH_TILE_ENTITY.create();
	}
}
