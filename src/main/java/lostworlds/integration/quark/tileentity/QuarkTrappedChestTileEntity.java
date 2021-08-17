package lostworlds.integration.quark.tileentity;

import lostworlds.integration.quark.init.QuarkTileEntities;

public class QuarkTrappedChestTileEntity extends QuarkChestTileEntity 
{
	public QuarkTrappedChestTileEntity() 
	{
		super(QuarkTileEntities.MOD_TRAPPED_CHEST);
	}

	protected void signalOpenCount() 
	{
		super.signalOpenCount();
		this.level.updateNeighborsAt(this.worldPosition.below(), this.getBlockState().getBlock());
	}
}
