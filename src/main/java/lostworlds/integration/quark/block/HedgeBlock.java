package lostworlds.integration.quark.block;

public class HedgeBlock //extends FenceBlock 
{
//	public static final ITag<Block> HEDGES = BlockTags.createOptional(new ResourceLocation("quark", "hedges"));
//	private static final BooleanProperty EXTEND = BooleanProperty.create("extend");
//
//	public HedgeBlock(Properties properties) 
//	{
//		super(properties);
//		this.registerDefaultState(this.defaultBlockState().setValue(EXTEND, false));
//	}
//
//	@Override
//	public boolean connectsTo(BlockState state, boolean isSideSolid, Direction direction) 
//	{
//		return state.getBlock().is(HEDGES);
//	}
//
//	@Override
//	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) 
//	{
//		return facing == Direction.UP && !state.getValue(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
//	}
//
//	@Override
//	public BlockState getStateForPlacement(BlockItemUseContext context) 
//	{
//		return super.getStateForPlacement(context).setValue(EXTEND, context.getLevel().getBlockState(context.getClickedPos().below()).getBlock().is(HEDGES));
//	}
//
//	@Override
//	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
//	{
//		if(state.getValue(WATERLOGGED)) 
//		{
//			world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
//		}
//
//		return facing == Direction.DOWN ? state.setValue(EXTEND, newState.getBlock().is(HEDGES)) : super.updateShape(state, facing, newState, world, pos, newPos);
//	}
//
//	@Override
//	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
//	{
//		super.createBlockStateDefinition(builder);
//		builder.add(EXTEND);
//	}
}
