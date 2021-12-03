package lostworlds.library.biome;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class DinosaurSpawn 
{
	public static void addDinosaursToOverworld(BiomeLoadingEvent event)
	{
		if(LostWorldsConfig.COMMON_CONFIG.dinosaursSpawnInOverworld.get())
		{
			if(event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.CHILESAURUS, LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.DESERT || event.getCategory() == Category.MESA)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.DIICTODON, LostWorldsConfig.COMMON_CONFIG.diictodonSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.diictodonSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.diictodonSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS || event.getCategory() == Category.SAVANNA)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.DILOPHOSAURUS, LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.BEACH)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.GREAT_AUK, LostWorldsConfig.COMMON_CONFIG.greatAukSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.greatAukSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.greatAukSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.KENTROSAURUS, LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.DESERT)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.LIAONINGOSAURUS, LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.OCEAN)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.OPHTHALMOSAURUS, LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.OCEAN)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.PALAEONISCUM, LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.SAVANNA)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.PSITTACOSAURUS, LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.DESERT)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.TETRACERATOPS, LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnGroupMaximum.get()));
			}
			
			if(event.getCategory() == Category.FOREST)
			{
				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityInit.ZEPHYROSAURUS, LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnGroupMaximum.get()));
			}
		}
	}
}
