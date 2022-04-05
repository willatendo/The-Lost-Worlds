package lostworlds.repack.tyrannotitanlib.tyranninetwork.packets;

import java.util.function.Supplier;

import net.minecraftforge.fml.network.NetworkEvent;

public interface ThreadSafePacket extends SimplePacket {
	@Override
	default void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> handleThreadsafe(context));
		context.setPacketHandled(true);
	}

	void handleThreadsafe(NetworkEvent.Context context);
}
