package lostworlds.library.item;

import lostworlds.library.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterPlantItem extends GroupedBlockItem
{
	public WaterPlantItem(Block block) 
	{
		super(block);
	}
	
	@Override
	protected boolean canPlace(BlockItemUseContext context, BlockState state) 
	{
		BlockPos pos = context.getClickedPos();
		World world = context.getLevel();
		return world.getBlockState(pos).getFluidState().getType() == Fluids.WATER && !(world.getBlockState(pos).getFluidState().getType() == Fluids.WATER);
	}
}
