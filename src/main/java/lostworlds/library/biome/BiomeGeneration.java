package lostworlds.library.biome;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BiomeInit;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
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
		
		if(LostWorldsConfig.COMMON_CONFIG.ginkgoForestShouldSpawn.get())
		{
			if(event.getName().equals(BiomeInit.GINKGO_FOREST.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST, LostWorldsConfig.COMMON_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST, Type.FOREST, Type.DENSE);
			}
			
			if(event.getName().equals(BiomeInit.GINKGO_FOREST_HILLS.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST_HILLS, LostWorldsConfig.COMMON_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST_HILLS, Type.FOREST, Type.DENSE, Type.HILLS);
			}
		}
	}
	
	public static void addFeaturesToOverworld(BiomeLoadingEvent event)
	{
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		
		if(LostWorldsConfig.COMMON_CONFIG.mudDisksInSwamps.get())
		{
			if(event.getCategory() == Category.SWAMP)
			{
				generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.MUD_DISK);
			}
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.PETRIFIED_ARAUCARIA);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedCalamitesTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.PETRIFIED_CALAMITES);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedConiferTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.PETRIFIED_CONIFER);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedGinkgoTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.PETRIFIED_GINKGO);
		}
	}
}
