package lostworlds.library.block;

import lostworlds.library.tileentity.ModSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ModWallSignBlock extends WallSignBlock implements ISign
{
	public ModWallSignBlock(Properties properties, WoodType woodType) 
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
