package lostworlds.repack.tyrannotitanlib.tyranninetwork.packets;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class UpdateHeldPagePacket implements ThreadSafePacket {
	private final Hand hand;
	private final String page;

	public UpdateHeldPagePacket(PacketBuffer buffer) {
		this.hand = buffer.readEnum(Hand.class);
		this.page = buffer.readUtf(100);
	}

	public UpdateHeldPagePacket(Hand hand, String page) {
		this.hand = hand;
		this.page = page;
	}

	@Override
	public void encode(PacketBuffer buf) {
		buf.writeEnum(hand);
		buf.writeUtf(this.page);
	}

	@Override
	public void handleThreadsafe(Context context) {
		PlayerEntity player = context.getSender();
		if (player != null && this.page != null) {
			ItemStack stack = player.getItemInHand(hand);
			if (!stack.isEmpty()) {
				TyrannobookHelper.writeSavedPageToBook(stack, this.page);
			}
		}
	}
}
