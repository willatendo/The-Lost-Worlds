package lostworlds.client.books.tyranninetwork.packets;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

public interface SimplePacket 
{
	void encode(FriendlyByteBuf buf);
	
	void handle(Supplier<NetworkEvent.Context> context);
}
