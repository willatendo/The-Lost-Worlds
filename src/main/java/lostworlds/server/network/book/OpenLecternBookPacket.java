package lostworlds.server.network.book;

import lostworlds.client.book.LecternBook;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent.Context;

public class OpenLecternBookPacket implements ThreadsafePacket {
	private final BlockPos pos;
	private final ItemStack book;

	public OpenLecternBookPacket(BlockPos pos, ItemStack stack) {
		this.pos = pos;
		this.book = stack;
	}

	public OpenLecternBookPacket(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.book = buffer.readItem();
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeItem(book);
	}

	@Override
	public void handleThreadsafe(Context context) {
		if (book.getItem() instanceof LecternBook) {
			((LecternBook) book.getItem()).openLecternScreenClient(pos, book);
		}
	}
}
