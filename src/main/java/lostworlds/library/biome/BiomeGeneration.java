package lostworlds.library.biome;

import java.util.List;

import com.google.common.collect.Lists;

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
		if(LostWorldsConfig.COMMON_CONFIG.araucariaForestShouldSpawn.get())
		{
			if(event.getName().equals(BiomeInit.ARAUCARIA_FOREST.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST, LostWorldsConfig.COMMON_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST, Type.FOREST, Type.CONIFEROUS);
			}
			
			if(event.getName().equals(BiomeInit.ARAUCARIA_FOREST_HILLS.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST_HILLS, LostWorldsConfig.COMMON_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}
		
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
		
		if(LostWorldsConfig.COMMON_CONFIG.sequoiaForestShouldSpawn.get())
		{
			if(event.getName().equals(BiomeInit.REDWOODS_FOREST.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST, LostWorldsConfig.COMMON_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST, Type.FOREST, Type.COLD, Type.CONIFEROUS);
			}
			
			if(event.getName().equals(BiomeInit.REDWOODS_FOREST_HILLS.getRegistryName())) 
			{
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST_HILLS, LostWorldsConfig.COMMON_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST_HILLS, Type.FOREST, Type.COLD, Type.CONIFEROUS);
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
		
		if(LostWorldsConfig.COMMON_CONFIG.cypressTreesInSwamps.get())
		{
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");
			
			if(biomes.contains(event.getName().toString()))
			{
				generation.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.SCANT_CYPRESS_TREES);
			}
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_ARAUCARIA);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedCalamitesTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_CALAMITES);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedConiferTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_CONIFER);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.petrifiedGinkgoTreeShouldSpawn.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_GINKGO);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.fossilsInOverworld.get())
		{				
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_TINY_NEST);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_SMALL_NEST);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_MEDIUM_NEST);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_LARGE_NEST);
		}
	}
}
