package lostworlds.server.impl.block.softdirt;

import java.util.Random;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class FeatherDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		int feather = rand.nextInt(DinoTypes.feathered().size());
		Block.popResource(world, pos, DinoTypes.feathered().get(feather).getFeather().get().getDefaultInstance());
	}
}
