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
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class WorldEvents {
	@SubscribeEvent
	public static void onWorldLoad(LevelEvent.Load event) {
		if (event.getLevel() instanceof ServerLevel serverLevel) {
			if (serverLevel.dimension() == Level.OVERWORLD) {
				upgradeDimension(LostWorldsDimensions.CRETACEOUS_LEVEL, serverLevel.getServer());
				upgradeDimension(LostWorldsDimensions.JURASSIC_LEVEL, serverLevel.getServer());
				upgradeDimension(LostWorldsDimensions.PERMIAN_LEVEL, serverLevel.getServer());
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
