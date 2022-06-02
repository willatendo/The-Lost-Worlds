package lostworlds.client.book;

import lostworlds.server.network.LostWorldsNetwork;
import lostworlds.server.network.book.OpenLecternBookPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface LecternBook {
	default boolean openLecternScreen(Level level, BlockPos pos, Player player, ItemStack stack) {
		LostWorldsNetwork.INSTANCE.sendTo(new OpenLecternBookPacket(pos, stack), player);
		return true;
	}

	void openLecternScreenClient(BlockPos pos, ItemStack stack);
}
