package lostworlds.server.network.book;

import lostworlds.client.book.BlockEntityHelper;
import lostworlds.client.book.BookHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraftforge.network.NetworkEvent.Context;

public class UpdateLecternPagePacket implements ThreadsafePacket {
	private final BlockPos pos;
	private final String page;

	public UpdateLecternPagePacket(BlockPos pos, String page) {
		this.pos = pos;
		this.page = page;
	}

	public UpdateLecternPagePacket(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.page = buffer.readUtf(100);
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.pos);
		buffer.writeUtf(this.page);
	}

	@Override
	public void handleThreadsafe(Context networkContext) {
		Player player = networkContext.getSender();
		if (player != null && this.page != null) {
			Level world = player.getCommandSenderWorld();
			BlockEntityHelper.get(LecternBlockEntity.class, world, this.pos).ifPresent(te -> {
				ItemStack stack = te.getBook();
				if (!stack.isEmpty()) {
					BookHelper.writeSavedPageToBook(stack, this.page);
				}
			});
		}
	}
}
