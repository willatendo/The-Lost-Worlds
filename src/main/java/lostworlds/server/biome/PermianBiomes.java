package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class PermianBiomes extends BiomeParts {
	public static Biome permianDesert() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianDesert(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.DESERT, 0.0F, 3.0F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianConiferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianConiferForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianGinkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianGinkgoForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianWindsweptHills() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianWindsweptHills(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));

		return biome(Precipitation.RAIN, BiomeCategory.EXTREME_HILLS, 0.8F, 0.3F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianOcean(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.5F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianWarmOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianCoralReef(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianAshyMedows() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianAshyMedows(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 5.0F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianDriedPlains() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianDriedPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 2.0F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianFloodBasalts() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianFloodBasalts(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(4.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 4.0F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianPlains() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.0F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianRiver() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianRiver(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.RIVER, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianShore() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianShore(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.BEACH, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}

	public static Biome permianMarsh() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianMarsh(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience.build(), generation.build(), spawns.build()).build();
	}
}
