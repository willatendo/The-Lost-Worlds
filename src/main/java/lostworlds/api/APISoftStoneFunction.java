package lostworlds.api;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

/*
 * Adds functions to the {@link SoftStoneBlock} like dropping different items.
 * 
 * Make sure to add it to {@link ImplInit}.SOFT_STONE_FUNCTIONS
 * 
 * Example {@link AnomalocarisFossilStoneFunction}
 */

public interface APISoftStoneFunction {
	/*
	 * Do whatever you want to do.
	 */
	void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack);
}
