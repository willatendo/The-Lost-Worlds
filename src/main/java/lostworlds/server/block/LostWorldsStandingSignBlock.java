package lostworlds.server.block;

import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class LostWorldsStandingSignBlock extends StandingSignBlock implements LostWorldsSign {
	public LostWorldsStandingSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader iBlockReader) {
		return LostWorldsBlockEntities.LOST_WORLDS_SIGN.create();
	}
}
