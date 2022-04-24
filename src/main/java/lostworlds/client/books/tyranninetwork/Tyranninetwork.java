package lostworlds.client.books.tyranninetwork;

import lostworlds.client.books.tyrannibook.TyrannoUtils;
import lostworlds.client.books.tyranninetwork.packets.DropLecternBookPacket;
import lostworlds.client.books.tyranninetwork.packets.OpenLecternBookPacket;
import lostworlds.client.books.tyranninetwork.packets.UpdateHeldPagePacket;
import lostworlds.client.books.tyranninetwork.packets.UpdateLecturnPagePacket;
import net.minecraftforge.fml.network.NetworkDirection;

public class Tyranninetwork {
	public static final NetworkWrapper INSTANCE = new NetworkWrapper(TyrannoUtils.rL("network"));

	public static void registerPackets() {
		INSTANCE.registerPacket(OpenLecternBookPacket.class, OpenLecternBookPacket::new, NetworkDirection.PLAY_TO_CLIENT);
		INSTANCE.registerPacket(UpdateLecturnPagePacket.class, UpdateLecturnPagePacket::new, NetworkDirection.PLAY_TO_SERVER);
		INSTANCE.registerPacket(UpdateHeldPagePacket.class, UpdateHeldPagePacket::new, NetworkDirection.PLAY_TO_SERVER);
		INSTANCE.registerPacket(DropLecternBookPacket.class, DropLecternBookPacket::new, NetworkDirection.PLAY_TO_SERVER);
	}
}
