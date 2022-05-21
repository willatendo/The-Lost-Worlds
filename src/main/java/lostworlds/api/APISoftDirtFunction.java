package lostworlds.api;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

/*
 * Adds functions to the {@link SoftDirtBlock} like dropping different items.
 * 
 * Make sure to add it to {@link ImplInit}.SOFT_DIRT_FUNCTIONS
 * 
 * Example {@link FossilizedFeatherDirtFunction}
 */

public interface APISoftDirtFunction {
	/*
	 * Do whatever you want to do.
	 */
	void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack);
}
