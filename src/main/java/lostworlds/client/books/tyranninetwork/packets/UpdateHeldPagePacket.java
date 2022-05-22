package lostworlds.client.books.tyranninetwork.packets;

import lostworlds.client.books.tyrannibook.client.TyrannobookHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class UpdateHeldPagePacket implements ThreadSafePacket {
	private final InteractionHand hand;
	private final String page;

	public UpdateHeldPagePacket(FriendlyByteBuf buffer) {
		this.hand = buffer.readEnum(InteractionHand.class);
		this.page = buffer.readUtf(100);
	}

	public UpdateHeldPagePacket(InteractionHand hand, String page) {
		this.hand = hand;
		this.page = page;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeEnum(hand);
		buf.writeUtf(this.page);
	}

	@Override
	public void handleThreadsafe(Context context) {
		Player player = context.getSender();
		if (player != null && this.page != null) {
			ItemStack stack = player.getItemInHand(hand);
			if (!stack.isEmpty()) {
				TyrannobookHelper.writeSavedPageToBook(stack, this.page);
			}
		}
	}
}
