package lostworlds.repack.tyrannotitanlib.tyranninetwork.packets;

import lostworlds.repack.tyrannotitanlib.tyrannibook.item.TyrannoLecternBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class OpenLecternBookPacket implements ThreadSafePacket {
	private final BlockPos pos;
	private final ItemStack book;

	public OpenLecternBookPacket(PacketBuffer buffer) {
		this.pos = buffer.readBlockPos();
		this.book = buffer.readItem();
	}

	public OpenLecternBookPacket(BlockPos pos, ItemStack book) {
		this.pos = pos;
		this.book = book;
	}

	@Override
	public void encode(PacketBuffer buffer) {
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
