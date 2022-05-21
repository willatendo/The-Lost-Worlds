package lostworlds.server.impl.block.softdirt;

import java.util.Random;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.block.Egg;
import lostworlds.server.block.LargeFossilisedEggBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.MediumFossilisedEggBlock;
import lostworlds.server.block.SmallFossilizedEggBlock;
import lostworlds.server.block.SoftDirtBlock;
import lostworlds.server.block.TinyFossilizedEggBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class SpawnEggDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		Egg egg = state.getValue(SoftDirtBlock.EGG);
		if (egg == Egg.TINY) {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.TINY_FOSSILISED_EGG.getDefaultState().setValue(TinyFossilizedEggBlock.EGGS, Integer.valueOf(rand.nextInt(19) + 1)));
		} else if (egg == Egg.SMALL) {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.SMALL_FOSSILISED_EGG.getDefaultState().setValue(SmallFossilizedEggBlock.EGGS, Integer.valueOf(rand.nextInt(10) + 1)));
		} else if (egg == Egg.MEDIUM) {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.MEDIUM_FOSSILISED_EGG.getDefaultState().setValue(MediumFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(6) + 1)));
		} else {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.LARGE_FOSSILISED_EGG.getDefaultState().setValue(LargeFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(3) + 1)));
		}
	}
}
