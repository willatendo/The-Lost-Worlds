package lostworlds.server.network;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.network.book.DropLecternBookPacket;
import lostworlds.server.network.book.OpenLecternBookPacket;
import lostworlds.server.network.book.OpenNamedBookPacket;
import lostworlds.server.network.book.UpdateHeldPagePacket;
import lostworlds.server.network.book.UpdateLecternPagePacket;
import net.minecraftforge.network.NetworkDirection;

public class LostWorldsNetwork {
	public static final NetworkWrapper INSTANCE = new NetworkWrapper(LostWorldsUtils.rL("network"));

	public static void registerPackets() {
		INSTANCE.registerPacket(OpenLecternBookPacket.class, OpenLecternBookPacket::new, NetworkDirection.PLAY_TO_CLIENT);
		INSTANCE.registerPacket(UpdateHeldPagePacket.class, UpdateHeldPagePacket::new, NetworkDirection.PLAY_TO_SERVER);
		INSTANCE.registerPacket(UpdateLecternPagePacket.class, UpdateLecternPagePacket::new, NetworkDirection.PLAY_TO_SERVER);
		INSTANCE.registerPacket(DropLecternBookPacket.class, DropLecternBookPacket::new, NetworkDirection.PLAY_TO_SERVER);
		INSTANCE.registerPacket(OpenNamedBookPacket.class, OpenNamedBookPacket::new, NetworkDirection.PLAY_TO_CLIENT);
	}
}
