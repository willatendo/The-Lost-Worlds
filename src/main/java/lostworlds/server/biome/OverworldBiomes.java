package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class OverworldBiomes extends BiomeParts {
	public static Biome araucariaForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.araucariaForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome coniferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.coniferForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome ginkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.ginkgoForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome redwoodsForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.redwoodsForest(generation);

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.4F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.4F, ambience, generation, defaultOverworldSpawns());
	}

	public static Biome volcano() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.volcano(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.5F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 2.5F, ambience, generation, spawns);
	}
}
