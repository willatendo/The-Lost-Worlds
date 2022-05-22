package lostworlds.server.impl.block.softdirt;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class FossilizedSkinDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Block.popResource(world, pos, LostWorldsItems.FOSSILIZED_SKIN_IMPRESSION.get().getDefaultInstance());
	}
}
