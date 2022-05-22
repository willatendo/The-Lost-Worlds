package lostworlds.client.books.tyranninetwork.packets;

import lostworlds.client.books.tyrannibook.item.TyrannoLecternBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class OpenLecternBookPacket implements ThreadSafePacket {
	private final BlockPos pos;
	private final ItemStack book;

	public OpenLecternBookPacket(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.book = buffer.readItem();
	}

	public OpenLecternBookPacket(BlockPos pos, ItemStack book) {
		this.pos = pos;
		this.book = book;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeItem(book);
	}

	@Override
	public void handleThreadsafe(Context context) {
		if (book.getItem() instanceof TyrannoLecternBookItem) {
			((TyrannoLecternBookItem) book.getItem()).openLecternScreenClient(pos, book);
		}
	}
}
