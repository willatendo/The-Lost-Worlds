package lostworlds.integration.quark.block;

public class VerticalSlabBlock //extends Block implements IWaterLoggable 
{
//	public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.create("type", VerticalSlabType.class);
//	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//
//	public VerticalSlabBlock(Properties properties) 
//	{	
//		super(properties);
//		this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, VerticalSlabType.NORTH).setValue(WATERLOGGED, false));
//	}
//	
//	@Override
//	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
//	{
//		builder.add(TYPE, WATERLOGGED);
//	}
//	
//	@Override
//	public boolean isAir(BlockState state) {
//		return state.getValue(TYPE) != VerticalSlabType.DOUBLE;
//	}
//
//	@Override
//	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) 
//	{
//		return state.getValue(TYPE).shape;
//	}
//
//	@Override
//	@Nullable
//	public BlockState getStateForPlacement(BlockItemUseContext context) 
//	{
//		BlockPos blockpos = context.getClickedPos();
//		BlockState blockstate = context.getLevel().getBlockState(blockpos);
//		if(blockstate.getBlock() == this)
//			return blockstate.setValue(TYPE, VerticalSlabType.DOUBLE).setValue(WATERLOGGED, false);
//		return this.defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(blockpos).getType() == Fluids.WATER).setValue(TYPE, VerticalSlabType.fromDirection(this.getDirectionForPlacement(context)));
//	}
//
//	private Direction getDirectionForPlacement(BlockItemUseContext context) 
//	{
//		Direction face = context.getClickedFace();
//		if(face.getAxis() != Direction.Axis.Y) return face;
//		Vector3d difference = context.getClickLocation().subtract(Vector3d.atBottomCenterOf(context.getClickedPos())).subtract(0.5, 0, 0.5);
//		return Direction.fromYRot(-Math.toDegrees(Math.atan2(difference.x(), difference.z()))).getOpposite();
//	}
//
//	@Override
//	public boolean canBeReplaced(BlockState state, @Nonnull BlockItemUseContext context) 
//	{
//		VerticalSlabType slabtype = state.getValue(TYPE);
//		return slabtype != VerticalSlabType.DOUBLE && context.getItemInHand().getItem() == this.asItem() && context.replacingClickedOnBlock() && (context.getClickedFace() == slabtype.direction && this.getDirectionForPlacement(context) == slabtype.direction);
//	}
//
//	@Override
//	public FluidState getFluidState(BlockState state) 
//	{
//		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
//	}
//
//	@Override
//	public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidStateIn) 
//	{
//		return state.getValue(TYPE) != VerticalSlabType.DOUBLE && IWaterLoggable.super.placeLiquid(world, pos, state, fluidStateIn);
//	}
//
//	@Override
//	public boolean canPlaceLiquid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluidIn) 
//	{
//		return state.getValue(TYPE) != VerticalSlabType.DOUBLE && IWaterLoggable.super.canPlaceLiquid(world, pos, state, fluidIn);
//	}
//
//	@Override
//	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) 
//	{
//		if(state.getValue(WATERLOGGED)) 
//		{
//			world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
//		}
//		return state;
//	}
//	
//	@Override
//	public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType type) 
//	{
//		return type == PathType.WATER && world.getFluidState(pos).is(FluidTags.WATER);
//	}
//
//	public static enum VerticalSlabType implements IStringSerializable 
//	{
//		NORTH(Direction.NORTH),
//		SOUTH(Direction.SOUTH),
//		WEST(Direction.WEST),
//		EAST(Direction.EAST),
//		DOUBLE(null);
//
//		private final String name;
//		@Nullable
//		public final Direction direction;
//		public final VoxelShape shape;
//
//		VerticalSlabType(@Nullable Direction direction) 
//		{
//			this.direction = direction;
//			this.name = direction == null ? "double" : direction.getSerializedName();
//			if(direction == null) 
//			{
//				this.shape = VoxelShapes.block();
//			} 
//			else 
//			{
//				boolean isNegativeAxis = direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE;
//				double min = isNegativeAxis ? 8 : 0;
//				double max = isNegativeAxis ? 16 : 8;
//				this.shape = direction.getAxis() == Direction.Axis.X ? Block.box(min, 0, 0, max, 16, 16) : Block.box(0, 0, min, 16, 16, max);
//			}
//		}
//
//		public static VerticalSlabType fromDirection(Direction direction) 
//		{
//			for(VerticalSlabType type : VerticalSlabType.values()) 
//			{
//				if(type.direction != null && direction == type.direction) 
//				{
//					return type;
//				}
//			}
//			return null;
//		}
//
//		@Override
//		public String toString() 
//		{
//			return this.name;
//		}
//
//		@Override
//		public String getSerializedName() 
//		{
//			return this.name;
//		}
//	}
}
