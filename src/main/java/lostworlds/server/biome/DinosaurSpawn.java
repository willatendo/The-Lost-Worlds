package lostworlds.server.biome;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class DinosaurSpawn {
	public static void addDinosaursToOverworld(BiomeLoadingEvent event) {
		if (LostWorldsConfig.SERVER_CONFIG.dinosaursSpawnInOverworld.get()) {
			if (event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.CHILESAURUS, LostWorldsConfig.SERVER_CONFIG.chilesaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.chilesaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.chilesaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.DESERT || event.getCategory() == Category.MESA) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.DIICTODON, LostWorldsConfig.SERVER_CONFIG.diictodonSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.diictodonSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.diictodonSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS || event.getCategory() == Category.SAVANNA) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.DILOPHOSAURUS, LostWorldsConfig.SERVER_CONFIG.dilophosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.dilophosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.dilophosaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.BEACH) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.GREAT_AUK, LostWorldsConfig.SERVER_CONFIG.greatAukSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.greatAukSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.greatAukSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.KENTROSAURUS, LostWorldsConfig.SERVER_CONFIG.kentrosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.kentrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.kentrosaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.DESERT) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.LIAONINGOSAURUS, LostWorldsConfig.SERVER_CONFIG.liaoningosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.liaoningosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.liaoningosaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.OCEAN) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.OPHTHALMOSAURUS, LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.OCEAN) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.PALAEONISCUM, LostWorldsConfig.SERVER_CONFIG.palaeoniscumSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.palaeoniscumSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.palaeoniscumSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.SAVANNA) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.PSITTACOSAURUS, LostWorldsConfig.SERVER_CONFIG.psittacosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.psittacosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.psittacosaurusSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.DESERT) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.TETRACERATOPS, LostWorldsConfig.SERVER_CONFIG.tetraceratopsSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.tetraceratopsSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.tetraceratopsSpawnGroupMaximum.get()));
			}

			if (event.getCategory() == Category.FOREST) {
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(LostWorldsEntities.ZEPHYROSAURUS, LostWorldsConfig.SERVER_CONFIG.zephyrosaurusSpawnWeight.get(), LostWorldsConfig.SERVER_CONFIG.zephyrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.SERVER_CONFIG.zephyrosaurusSpawnGroupMaximum.get()));
			}
		}
	}
}
