package lostworlds.integration.quark.block;

public class QuarkTrappedChestBlock //extends ChestBlock implements IChestBlock 
{
//	public final String type;
//
//	public QuarkTrappedChestBlock(String type, Properties properties) 
//	{
//		super(properties, () -> QuarkTileEntities.MOD_TRAPPED_CHEST);
//		this.type = type;
//	}
//
//	@Override
//	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) 
//	{
//		return false;
//	}
//
//	@Override
//	public TileEntity newBlockEntity(IBlockReader worldIn) 
//	{
//		return new QuarkTrappedChestTileEntity();
//	}
//
//	@Override
//	public String getChestType() 
//	{
//		return this.type;
//	}
//
//	@Override
//	protected Stat<ResourceLocation> getOpenChestStat() 
//	{
//		return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
//	}
//
//	@Override
//	public boolean isSignalSource(BlockState state) 
//	{
//		return true;
//	}
//
//	@Override
//	public int getSignal(BlockState state, IBlockReader reader, BlockPos pos, Direction direction) 
//	{
//		return MathHelper.clamp(ChestTileEntity.getOpenCount(reader, pos), 0, 15);
//	}
//
//	@Override
//	public int getDirectSignal(BlockState state, IBlockReader reader, BlockPos pos, Direction direction) 
//	{
//		return direction == Direction.UP ? state.getSignal(reader, pos, direction) : 0;
//	}
}
