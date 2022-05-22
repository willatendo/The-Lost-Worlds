package lostworlds.server.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class WaterPlantItem extends BlockItem {
	public WaterPlantItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		BlockPos pos = context.getClickedPos();
		Level world = context.getLevel();
		return world.getBlockState(pos).getFluidState().getType() == Fluids.WATER && world.getBlockState(pos.above()).is(Blocks.AIR);
	}
}
