package lostworlds.repack.tyrannotitanlib.tyranninetwork;

import lostworlds.repack.tyrannotitanlib.tyrannibook.TyrannoUtils;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.DropLecternBookPacket;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.OpenLecternBookPacket;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.UpdateHeldPagePacket;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.UpdateLecturnPagePacket;
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
