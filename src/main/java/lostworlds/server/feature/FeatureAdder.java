package lostworlds.server.feature;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureAdder {
	public static void addFeature(BiomeLoadingEvent event, GenerationStep.Decoration decoration, Holder<PlacedFeature> feature) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		generation.addFeature(decoration, feature);
	}

	public static void addOre(BiomeLoadingEvent event, Holder<PlacedFeature> ore) {
		addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, ore);
	}

	public static void addBiome(BiomeLoadingEvent event, ResourceLocation loc, BiomeType temperature, ResourceKey<Biome> key, int weight, Type... types) {
		if (event.getName().equals(loc)) {
			BiomeManager.addBiome(temperature, new BiomeManager.BiomeEntry(key, weight));
			BiomeDictionary.addTypes(key, types);
		}
	}

	public static void addSpawn(BiomeLoadingEvent event, MobCategory classification, EntityType toSpawn, int spawnWeight, int spawnGroupMinimum, int spawnGroupMaximum) {
		event.getSpawns().addSpawn(classification, new SpawnerData(toSpawn, spawnWeight, spawnGroupMinimum, spawnGroupMaximum));
	}
}
