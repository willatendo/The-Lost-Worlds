package lostworlds.server.network;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lostworlds.server.network.book.SimplePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkWrapper {
	public final SimpleChannel network;
	private int id = 0;
	private static final String PROTOCOL_VERSION = Integer.toString(1);

	public NetworkWrapper(ResourceLocation channelName) {
		this.network = NetworkRegistry.ChannelBuilder.named(channelName).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();
	}

	public <MSG extends SimplePacket> void registerPacket(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> buffer, @Nullable NetworkDirection networkDirection) {
		registerPacket(clazz, SimplePacket::encode, buffer, SimplePacket::handle, networkDirection);
	}

	public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, FriendlyByteBuf> consumer, Function<FriendlyByteBuf, MSG> buffer, BiConsumer<MSG, Supplier<Context>> networkContext, @Nullable NetworkDirection networkDirection) {
		this.network.registerMessage(this.id++, clazz, consumer, buffer, networkContext, Optional.ofNullable(networkDirection));
	}

	public void sendToServer(Object msg) {
		this.network.sendToServer(msg);
	}

	public void send(PacketDistributor.PacketTarget target, Object msg) {
		network.send(target, msg);
	}

	public void sendVanillaPacket(Packet<?> packet, Entity entity) {
		if (entity instanceof ServerPlayer && ((ServerPlayer) entity).connection != null) {
			((ServerPlayer) entity).connection.send(packet);
		}
	}

	public void sendTo(Object msg, Player player) {
		if (player instanceof ServerPlayer) {
			sendTo(msg, (ServerPlayer) player);
		}
	}

	public void sendTo(Object msg, ServerPlayer serverPlayer) {
		if (!(serverPlayer instanceof FakePlayer)) {
			network.sendTo(msg, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public void sendToClientsAround(Object msg, ServerLevel serverLevel, BlockPos pos) {
		LevelChunk chunk = serverLevel.getChunkAt(pos);
		network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
	}

	public void sendToTrackingAndSelf(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
	}

	public void sendToTracking(Object msg, Entity entity) {
		this.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
	}
}
