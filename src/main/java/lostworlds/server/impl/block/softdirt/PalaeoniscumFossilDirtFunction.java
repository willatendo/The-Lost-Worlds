package lostworlds.server.impl.block.softdirt;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class PalaeoniscumFossilDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Block.popResource(world, pos, DinoTypes.PALAEONISCUM.getSkeletonPick().get().asItem().getDefaultInstance());
	}
}
