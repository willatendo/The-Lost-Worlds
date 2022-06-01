package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class JurassicBiomes extends BiomeParts {
	public static Biome jurassicDesert() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicDesert(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.DESERT, 0.0F, 2.0F, ambience, generation, spawns);
	}

	public static Biome jurassicAraucariaForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicAraucariaForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, spawns);
	}

	public static Biome jurassicConiferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicConiferForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, spawns);
	}

	public static Biome jurassicGinkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicGinkgoForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.8F, ambience, generation, spawns);
	}

	public static Biome jurassicRedwoodsForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicRedwoodsForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome jurassicWindsweptHills() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicWindsweptHills(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.EXTREME_HILLS, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome jurassicVolcanicRange() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicVolcanicRange(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.5F));

		return biome(Precipitation.RAIN, BiomeCategory.EXTREME_HILLS, 0.8F, 2.5F, ambience, generation, spawns);
	}

	public static Biome jurassicOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicOcean(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome jurassicWarmOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicCoralReef(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(WARM_OCEAN_WATER_COLOUR).waterFogColor(WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome jurassicPlains() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome jurassicRiver() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicRiver(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.RIVER, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome jurassicShore() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicShore(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.BEACH, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome jurassicMarsh() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicMarsh(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome jurassicSwamp() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicSwamp(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome jurassicFen() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicFen(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome jurassicBog() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.jurassicBog(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}
}
