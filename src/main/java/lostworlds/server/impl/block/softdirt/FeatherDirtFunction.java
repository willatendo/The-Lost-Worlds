package lostworlds.server.impl.block.softdirt;

import java.util.Random;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class FeatherDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		int feather = rand.nextInt(DinoTypes.feathered().size());
		Block.popResource(world, pos, DinoTypes.feathered().get(feather).getFeather().get().getDefaultInstance());
	}
}
