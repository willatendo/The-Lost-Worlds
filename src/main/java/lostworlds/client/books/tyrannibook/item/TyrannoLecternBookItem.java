package lostworlds.client.books.tyrannibook.item;

import lostworlds.client.books.tyranninetwork.Tyranninetwork;
import lostworlds.client.books.tyranninetwork.packets.OpenLecternBookPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface TyrannoLecternBookItem {
	default boolean openLecternScreen(World world, BlockPos pos, PlayerEntity player, ItemStack stack) {
		Tyranninetwork.INSTANCE.sendTo(new OpenLecternBookPacket(pos, stack), player);
		return true;
	}

	void openLecternScreenClient(BlockPos pos, ItemStack stack);
}
