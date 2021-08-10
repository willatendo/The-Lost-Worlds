package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.library.ModMaterials;
import lostworlds.library.block.base.BasicBlockAndItem;
import lostworlds.library.tileentity.FossilCleanerTileEntity;
import lostworlds.library.tileentity.TimeMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
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

public class TimeMachineBlock extends Block implements ITileEntityProvider
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 11, 16);
	
	protected TimeMachineBlock() 
	{
		super(Properties.of(ModMaterials.MAGIC).harvestLevel(4).requiresCorrectToolForDrops().strength(50.0F, 1200.0F));
	}
	
	@Override
	public boolean useShapeForLightOcclusion(BlockState state) 
	{
		return true;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	public BlockRenderType getRenderShape(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) 
	{
		return new TimeMachineTileEntity();
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
			if(tile instanceof TimeMachineTileEntity) 
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
			if(tileentity instanceof TimeMachineTileEntity) 
			{
				((FossilCleanerTileEntity)tileentity).setCustomName(stack.getHoverName());
			}
		}
	}
	
	public static Block create()
	{
		return BasicBlockAndItem.create("time_machine", new TimeMachineBlock());
	}
}
