package lostworlds.library.block;

import lostworlds.library.block.base.BasicBlockAndItem;
import lostworlds.library.container.ArchaeologyTableContianer;
import lostworlds.library.util.ModUtil;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class ArchaeologyTable extends Block implements IWaterLoggable
{
	private static final ITextComponent NAME = ModUtil.tTC("container", "archaeology_table");
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;	

	protected ArchaeologyTable() 
	{
		super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.PICKAXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER;
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(flag));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		super.createBlockStateDefinition(builder);
		builder.add(WATERLOGGED);
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		if(stateIn.getValue(WATERLOGGED)) 
		{
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	 
	@Override
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) 
	{
		if(world.isClientSide)
		{
			return ActionResultType.SUCCESS;
		} 
		else 
		{
			player.openMenu(state.getMenuProvider(world, pos));
			return ActionResultType.CONSUME;
		}
	}
	
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) 
	{
		return new SimpleNamedContainerProvider((windowId, playerInv, p_220270_4_) -> 
		{
			return new ArchaeologyTableContianer(windowId, playerInv, IWorldPosCallable.create(world, pos));
		}, NAME);
	}
	
	public static Block create()
	{
		return BasicBlockAndItem.create("archaeology_table", new ArchaeologyTable());
	}
}
