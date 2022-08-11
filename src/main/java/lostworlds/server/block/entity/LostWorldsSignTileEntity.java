package lostworlds.server.block.entity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class LostWorldsSignTileEntity extends SignTileEntity {
	@Override
	public TileEntityType<?> getType() {
		return LostWorldsBlockEntities.LOST_WORLDS_SIGN.get();
	}
}
