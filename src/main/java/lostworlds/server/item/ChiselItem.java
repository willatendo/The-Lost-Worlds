package lostworlds.server.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChiselItem extends Item {
	public ChiselItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		if (player.isCreative()) {
			return false;
		} else {
			return true;
		}
	}
}
