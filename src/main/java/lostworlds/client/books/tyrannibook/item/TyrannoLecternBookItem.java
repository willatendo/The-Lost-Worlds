package lostworlds.client.books.tyrannibook.item;

import lostworlds.client.books.tyranninetwork.Tyranninetwork;
import lostworlds.client.books.tyranninetwork.packets.OpenLecternBookPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface TyrannoLecternBookItem {
	default boolean openLecternScreen(Level world, BlockPos pos, Player player, ItemStack stack) {
		Tyranninetwork.INSTANCE.sendTo(new OpenLecternBookPacket(pos, stack), player);
		return true;
	}

	void openLecternScreenClient(BlockPos pos, ItemStack stack);
}
