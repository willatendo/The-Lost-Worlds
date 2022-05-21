package lostworlds.server.impl.block.softdirt;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class FossilizedFeatherDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Block.popResource(world, pos, LostWorldsItems.FOSSILIZED_FEATHER.get().getDefaultInstance());
	}
}
