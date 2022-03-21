package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChiselItem extends Item {
	public ChiselItem() {
		super(new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(1).defaultDurability(32));
	}

	@Override
	public boolean canAttackBlock(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
		if (entity.isCreative()) {
			return false;
		} else {
			return true;
		}
	}
}
