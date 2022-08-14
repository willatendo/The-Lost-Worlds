package lostworlds.server.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.LostWorldsDimensions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class WorldEvents {
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerLevel) {
			ServerLevel world = (ServerLevel) event.getWorld();
			if (world.dimension() == Level.OVERWORLD) {
				upgradeDimension(LostWorldsDimensions.CRETACEOUS_LEVEL, world.getServer());
				upgradeDimension(LostWorldsDimensions.JURASSIC_LEVEL, world.getServer());
				upgradeDimension(LostWorldsDimensions.PERMIAN_LEVEL, world.getServer());
			}
		}
	}

	private static void upgradeDimension(ResourceKey<Level> levelKey, MinecraftServer server) {
		LevelStorageSource.LevelStorageAccess save = server.storageSource;

		File oldDimension = save.getLevelPath(new LevelResource("lostworlds/" + levelKey.location().getPath())).toFile();
		Path newDimension = save.getDimensionPath(levelKey);
		if (oldDimension.exists() && !newDimension.toFile().exists()) {
			try {
				FileUtils.moveDirectory(oldDimension, newDimension.toFile());
			} catch (IOException e) {
			}
		}
	}
}
