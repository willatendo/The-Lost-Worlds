package lostworlds.client.books.tyranninetwork.packets;

import lostworlds.client.books.tyrannibook.client.TyrannobookHelper;
import lostworlds.client.books.tyranninetwork.util.TileEntityHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class UpdateLecturnPagePacket implements ThreadSafePacket {
	private final BlockPos pos;
	private final String page;

	public UpdateLecturnPagePacket(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.page = buffer.readUtf(100);
	}

	public UpdateLecturnPagePacket(BlockPos pos, String page) {
		this.pos = pos;
		this.page = page;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeBlockPos(pos);
		buf.writeUtf(page);
	}

	@Override
	public void handleThreadsafe(Context context) {
		Player player = context.getSender();
		if (player != null && this.page != null) {
			Level world = player.getCommandSenderWorld();
			TileEntityHelper.getTile(LecternBlockEntity.class, world, this.pos).ifPresent(te -> {
				ItemStack stack = te.getBook();
				if (!stack.isEmpty()) {
					TyrannobookHelper.writeSavedPageToBook(stack, this.page);
				}
			});
		}
	}
}
