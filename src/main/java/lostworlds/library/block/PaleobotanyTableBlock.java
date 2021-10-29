package lostworlds.library.block;

import lostworlds.library.block.builder.BlockAndItemBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class PaleobotanyTableBlock extends Block implements IWaterLoggable
{
//	private static final ITextComponent NAME = ModUtils.tTC("container", "paleontology_table");
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;	

	public PaleobotanyTableBlock() 
	{
		super(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion());
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

//	@Override
//	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) 
//	{
//		if(world.isClientSide)
//		{
//			return ActionResultType.SUCCESS;
//		} 
//		else 
//		{
//			player.openMenu(state.getMenuProvider(world, pos));
//			return ActionResultType.CONSUME;
//		}
//	}
	
//	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) 
//	{
//		return new SimpleNamedContainerProvider((windowId, playerInv, p_220270_4_) -> 
//		{
//			return new PaleontologyTableContainer(windowId, playerInv, IWorldPosCallable.create(world, pos));
//		}, NAME);
//	}
	
	@Override
	public float getShadeBrightness(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return 1.0F;
	}
	
	public static Block create()
	{
		return BlockAndItemBuilder.create("paleobotany_table", new PaleobotanyTableBlock());
	}
}
