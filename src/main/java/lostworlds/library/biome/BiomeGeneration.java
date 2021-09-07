package lostworlds.library.biome;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BiomeInit;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class BiomeGeneration 
{	
	public static void addBiomesToOverworld(BiomeLoadingEvent event) 
	{
		if(LostWorldsConfig.COMMON_CONFIG.coniferForestShouldSpawn.get())
		{
			if(event.getName().equals(BiomeInit.CONIFER_FOREST.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST, LostWorldsConfig.COMMON_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST, Type.FOREST, Type.CONIFEROUS);
			}
			
			if(event.getName().equals(BiomeInit.CONIFER_FOREST_HILLS.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST_HILLS, LostWorldsConfig.COMMON_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}
		
		
	}
}
