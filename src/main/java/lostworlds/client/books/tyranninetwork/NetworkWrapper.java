package lostworlds.client.books.tyranninetwork;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lostworlds.client.books.tyranninetwork.packets.SimplePacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkWrapper {
	public final SimpleChannel network;
	private int id = 0;
	private static final String PROTOCOL_VERSION = Integer.toString(1);

	public NetworkWrapper(ResourceLocation channelName) {
		this.network = NetworkRegistry.ChannelBuilder.named(channelName).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();
	}

	public <MSG extends SimplePacket> void registerPacket(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder, @Nullable NetworkDirection direction) {
		registerPacket(clazz, SimplePacket::encode, decoder, SimplePacket::handle, direction);
	}

	public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<Context>> consumer, @Nullable NetworkDirection direction) {
		this.network.registerMessage(this.id++, clazz, encoder, decoder, consumer, Optional.ofNullable(direction));
	}

	public void sendToServer(Object msg) {
		this.network.sendToServer(msg);
	}

	public void send(PacketDistributor.PacketTarget target, Object message) {
		network.send(target, message);
	}

	public void sendVanillaPacket(Packet<?> packet, Entity player) {
		if (player instanceof ServerPlayer && ((ServerPlayer) player).connection != null) {
			((ServerPlayer) player).connection.send(packet);
		}
	}

	public void sendTo(Object msg, Player player) {
		if (player instanceof ServerPlayer) {
			sendTo(msg, (ServerPlayer) player);
		}
	}

	public void sendTo(Object msg, ServerPlayer player) {
		if (!(player instanceof FakePlayer)) {
			network.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public void sendToClientsAround(Object msg, ServerLevel serverWorld, BlockPos position) {
		LevelChunk chunk = serverWorld.getChunkAt(position);
		network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
	}

	public void sendToTrackingAndSelf(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
	}

	public void sendToTracking(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
	}
}
