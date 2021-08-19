package lostworlds.integration.buzzierbees.tileentity;

import lostworlds.integration.buzzierbees.init.BuzzierBeesTileEntities;
import net.minecraft.tileentity.BeehiveTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class BuzzierBeesBehiveTileEntity extends BeehiveTileEntity
{
	public BuzzierBeesBehiveTileEntity() 
	{
		super();
	}

	@Override
	public TileEntityType<?> getType() 
	{
		return BuzzierBeesTileEntities.MOD_BEEHIVE;
	}
}
