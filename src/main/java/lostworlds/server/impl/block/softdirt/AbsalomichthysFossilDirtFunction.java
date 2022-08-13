package lostworlds.server.impl.block.softdirt;

import lostworlds.api.APISoftDirtFunction;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AbsalomichthysFossilDirtFunction implements APISoftDirtFunction {
	@Override
	public void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Block.popResource(world, pos, AncientCreatures.ABSALOMICHTHYS.getSkeletonPick().get().asItem().getDefaultInstance());
	}
}