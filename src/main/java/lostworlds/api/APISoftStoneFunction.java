package lostworlds.api;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

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
	void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack);
}
