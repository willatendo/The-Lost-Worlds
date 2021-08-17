package lostworlds.integration.quark.block;

import lostworlds.integration.quark.init.QuarkTileEntities;
import lostworlds.integration.quark.tileentity.QuarkTrappedChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;

public class QuarkTrappedChestBlock extends ChestBlock implements IChestBlock 
{
	public final String type;

	public QuarkTrappedChestBlock(String type, Properties props) 
	{
		super(props, () -> QuarkTileEntities.MOD_TRAPPED_CHEST);
		this.type = type;
	}

	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) 
	{
		return false;
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader worldIn) 
	{
		return new QuarkTrappedChestTileEntity();
	}

	@Override
	public String getChestType() 
	{
		return this.type;
	}

	@Override
	protected Stat<ResourceLocation> getOpenChestStat() 
	{
		return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
	}

	@Override
	public boolean isSignalSource(BlockState state) 
	{
		return true;
	}

	@Override
	public int getSignal(BlockState state, IBlockReader reader, BlockPos pos, Direction direction) 
	{
		return MathHelper.clamp(ChestTileEntity.getOpenCount(reader, pos), 0, 15);
	}

	@Override
	public int getDirectSignal(BlockState state, IBlockReader reader, BlockPos pos, Direction direction) 
	{
		return direction == Direction.UP ? state.getSignal(reader, pos, direction) : 0;
	}
}
