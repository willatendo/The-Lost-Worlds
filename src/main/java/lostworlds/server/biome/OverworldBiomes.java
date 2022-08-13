package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class OverworldBiomes extends BiomeParts {
	public static Biome.BiomeBuilder araucariaForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.araucariaForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder().waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.FOREST, 0.0F, 2.0F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome.BiomeBuilder coniferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.coniferForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder().waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome.BiomeBuilder ginkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.ginkgoForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder().waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome.BiomeBuilder redwoodsForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.redwoodsForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder().waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.4F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.4F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome.BiomeBuilder volcano() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.volcano(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder().waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.5F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 2.5F, ambience, generation, new MobSpawnSettings.Builder());
	}
}
