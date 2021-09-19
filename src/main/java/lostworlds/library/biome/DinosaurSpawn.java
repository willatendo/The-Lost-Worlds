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
		}
	}
}
