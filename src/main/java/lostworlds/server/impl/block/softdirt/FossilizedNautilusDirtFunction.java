package lostworlds.server.impl.block.softdirt;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class FossilizedNautilusDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
	}
}
