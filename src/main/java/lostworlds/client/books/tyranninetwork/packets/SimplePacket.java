package lostworlds.client.books.tyranninetwork.packets;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public interface SimplePacket 
{
	void encode(PacketBuffer buf);
	
	void handle(Supplier<NetworkEvent.Context> context);
}
