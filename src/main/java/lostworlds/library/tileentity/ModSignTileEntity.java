package lostworlds.library.tileentity;

import lostworlds.content.server.init.TileEntityInit;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class ModSignTileEntity extends SignTileEntity
{
	@Override
	public TileEntityType<?> getType() 
	{
		return TileEntityInit.SIGN_TILE_ENTITY.get();
	}
}
