package lostworlds.server.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterPlantItem extends BlockItem {
	public WaterPlantItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		BlockPos pos = context.getClickedPos();
		World world = context.getLevel();
		return world.getBlockState(pos).getFluidState().getType() == Fluids.WATER && world.getBlockState(pos.above()).is(Blocks.AIR);
	}
}
