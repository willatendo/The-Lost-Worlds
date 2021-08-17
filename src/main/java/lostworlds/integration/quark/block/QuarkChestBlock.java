package lostworlds.integration.quark.block;

import lostworlds.integration.quark.init.QuarkTileEntities;
import lostworlds.integration.quark.tileentity.QuarkChestTileEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class QuarkChestBlock extends ChestBlock implements IChestBlock
{
	public final String type;

	public QuarkChestBlock(String type, Properties properties)
	{
		super(properties, () -> QuarkTileEntities.MOD_CHEST);
		this.type = type;
	}
	
	@Override
	public TileEntity newBlockEntity(IBlockReader reader) 
	{
		return new QuarkChestTileEntity();
	}

	@Override
	public String getChestType() 
	{
		return type;
	}
}
