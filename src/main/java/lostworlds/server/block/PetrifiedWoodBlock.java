package lostworlds.server.block;

import java.util.function.Supplier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class PetrifiedWoodBlock extends RotatedPillarBlock {
	private final Supplier<ItemStack> sample;

	public PetrifiedWoodBlock(Supplier<ItemStack> sample, Properties properties) {
		super(properties);
		this.sample = sample;
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (toolAction == ToolActions.AXE_SCRAPE) {
			this.popResource(context.getLevel(), context.getClickedPos(), this.sample.get());
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}
