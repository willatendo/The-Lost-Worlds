package lostworlds.integration.quark.tileentity;

import lostworlds.integration.quark.init.QuarkTileEntities;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

public class QuarkChestTileEntity extends ChestTileEntity 
{
	protected QuarkChestTileEntity(TileEntityType<?> typeIn) 
	{
		super(typeIn);
	}

	public QuarkChestTileEntity() 
	{
		super(QuarkTileEntities.MOD_CHEST);
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(worldPosition.getX() - 1, worldPosition.getY(), worldPosition.getZ() - 1, worldPosition.getX() + 2, worldPosition.getY() + 2, worldPosition.getZ() + 2);
	}
}
