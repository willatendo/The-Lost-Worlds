package lostworlds.server.feature;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureAdder {
	public static void addFeature(BiomeLoadingEvent event, GenerationStage.Decoration decoration, ConfiguredFeature feature) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		generation.addFeature(decoration, feature);
	}

	public static void addOre(BiomeLoadingEvent event, ConfiguredFeature ore) {
		addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ore);
	}

	public static void addBiome(BiomeLoadingEvent event, ResourceLocation loc, BiomeType temperature, RegistryKey<Biome> key, int weight, Type... types) {
		if (event.getName().equals(loc)) {
			BiomeManager.addBiome(temperature, new BiomeManager.BiomeEntry(key, weight));
			BiomeDictionary.addTypes(key, types);
		}
	}

	public static void addSpawn(BiomeLoadingEvent event, EntityClassification classification, EntityType toSpawn, int spawnWeight, int spawnGroupMinimum, int spawnGroupMaximum) {
		event.getSpawns().addSpawn(classification, new Spawners(toSpawn, spawnWeight, spawnGroupMinimum, spawnGroupMaximum));
	}
}
