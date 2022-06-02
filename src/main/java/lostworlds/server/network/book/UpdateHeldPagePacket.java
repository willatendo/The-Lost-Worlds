package lostworlds.server.network.book;

import lostworlds.client.book.BookHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent.Context;

public class UpdateHeldPagePacket implements ThreadsafePacket {
	private final InteractionHand hand;
	private final String page;

	public UpdateHeldPagePacket(InteractionHand hand, String page) {
		this.hand = hand;
		this.page = page;
	}

	public UpdateHeldPagePacket(FriendlyByteBuf buffer) {
		this.hand = buffer.readEnum(InteractionHand.class);
		this.page = buffer.readUtf(100);
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.hand);
		buffer.writeUtf(this.page);
	}

	@Override
	public void handleThreadsafe(Context networkContext) {
		Player player = networkContext.getSender();
		if (player != null && this.page != null) {
			ItemStack stack = player.getItemInHand(hand);
			if (!stack.isEmpty()) {
				BookHelper.writeSavedPageToBook(stack, this.page);
			}
		}
	}
}
