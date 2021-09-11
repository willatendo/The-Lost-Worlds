package lostworlds.library.block.builder;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TEBlockBuilder extends Block implements ITileEntityProvider 
{
	public TEBlockBuilder(Properties properties) 
	{
		super(properties);
	}

	@Override
	public boolean triggerEvent(BlockState state, World world, BlockPos pos, int i1, int i2) 
	{
		super.triggerEvent(state, world, pos, i1, i2);
		TileEntity tileentity = world.getBlockEntity(pos);
		return tileentity == null ? false : tileentity.triggerEvent(i1, i2);
	}

	@Override
	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) 
	{
		TileEntity tileentity = world.getBlockEntity(pos);
		return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
	}
}
