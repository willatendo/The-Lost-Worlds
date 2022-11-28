package lostworlds.server.events;

import java.util.HashMap;
import java.util.Map;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.spawner.FossilPoachingGroupSpawner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class SpawnHandlerEvents {
	private static final Map<ResourceLocation, FossilPoachingGroupSpawner> spawners = new HashMap<>();

	@SubscribeEvent
	public static void onServerStart(ServerStartedEvent event) {
		spawners.put(BuiltinDimensionTypes.OVERWORLD_EFFECTS, new FossilPoachingGroupSpawner());
	}

	@SubscribeEvent
	public static void onServerStart(ServerStoppedEvent event) {
		spawners.clear();
	}

	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase != TickEvent.Phase.START) {
			return;
		}

		if (event.side != LogicalSide.SERVER) {
			return;
		}

		FossilPoachingGroupSpawner spawner = spawners.get(event.level.dimension().location());
		if (spawner != null) {
			spawner.tick((ServerLevel) event.level);
		}
	}
}
