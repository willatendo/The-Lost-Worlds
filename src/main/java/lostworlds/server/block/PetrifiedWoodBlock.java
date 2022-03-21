package lostworlds.server.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class PetrifiedWoodBlock extends RotatedPillarBlock {
	private final Item sample;

	public PetrifiedWoodBlock(Properties properties, Item sample) {
		super(properties);
		this.sample = sample;
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.AXE)
			state.getBlock().popResource(world, pos, sample.getDefaultInstance());
		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}
}
