package lostworlds.library.block;

import lostworlds.library.tileentity.ModSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ModStandingSignBlock extends StandingSignBlock implements ISign
{
	public ModStandingSignBlock(Properties properties, WoodType woodType) 
	{
		super(properties, woodType);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader world) 
	{
		return new ModSignTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return this.newBlockEntity(world);
	}
}
