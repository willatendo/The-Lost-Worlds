package lostworlds.server.network.book;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public interface SimplePacket {
	void encode(FriendlyByteBuf buffer);

	void handle(Supplier<NetworkEvent.Context> networkContext);
}
