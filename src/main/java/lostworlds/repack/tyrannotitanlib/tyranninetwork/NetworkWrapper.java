package lostworlds.repack.tyrannotitanlib.tyranninetwork;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.SimplePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;
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

	public <MSG extends SimplePacket> void registerPacket(Class<MSG> clazz, Function<PacketBuffer, MSG> decoder, @Nullable NetworkDirection direction) {
		registerPacket(clazz, SimplePacket::encode, decoder, SimplePacket::handle, direction);
	}

	public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<Context>> consumer, @Nullable NetworkDirection direction) {
		this.network.registerMessage(this.id++, clazz, encoder, decoder, consumer, Optional.ofNullable(direction));
	}

	public void sendToServer(Object msg) {
		this.network.sendToServer(msg);
	}

	public void send(PacketDistributor.PacketTarget target, Object message) {
		network.send(target, message);
	}

	public void sendVanillaPacket(IPacket<?> packet, Entity player) {
		if (player instanceof ServerPlayerEntity && ((ServerPlayerEntity) player).connection != null) {
			((ServerPlayerEntity) player).connection.send(packet);
		}
	}

	public void sendTo(Object msg, PlayerEntity player) {
		if (player instanceof ServerPlayerEntity) {
			sendTo(msg, (ServerPlayerEntity) player);
		}
	}

	public void sendTo(Object msg, ServerPlayerEntity player) {
		if (!(player instanceof FakePlayer)) {
			network.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public void sendToClientsAround(Object msg, ServerWorld serverWorld, BlockPos position) {
		Chunk chunk = serverWorld.getChunkAt(position);
		network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
	}

	public void sendToTrackingAndSelf(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
	}

	public void sendToTracking(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
	}
}
