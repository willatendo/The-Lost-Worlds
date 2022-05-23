package lostworlds.server.world;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class StructureGen {
	public static void init(BiomeLoadingEvent event) {
		if (LostWorldsUtils.SERVER_CONFIG.blackMarketShouldSpawn.get()) {
			if (LostWorldsUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> LostWorldsConfiguredStructures.CONFIGURED_BLACK_MARKET);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.meteoriteShouldSpawn.get()) {
			if (LostWorldsUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> LostWorldsConfiguredStructures.CONFIGURED_METEORITE);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> LostWorldsConfiguredStructures.CONFIGURED_SURFACE_FOSSIL);
			}
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> LostWorldsConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL);
			}
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> LostWorldsConfiguredStructures.CONFIGURED_TRACE_FOSSIL);
			}
		}
	}
}
