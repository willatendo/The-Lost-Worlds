package lostworlds.server.network.book;

import java.util.function.Supplier;

import net.minecraftforge.network.NetworkEvent;

public interface ThreadsafePacket extends SimplePacket {
	@Override
	default void handle(Supplier<NetworkEvent.Context> networkContext) {
		NetworkEvent.Context context = networkContext.get();
		context.enqueueWork(() -> handleThreadsafe(context));
		context.setPacketHandled(true);
	}

	void handleThreadsafe(NetworkEvent.Context networkContext);
}
