package lostworlds.server.biome;

import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
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

		return biome(Precipitation.NONE, BiomeCategory.DESERT, 0.0F, 3.0F, ambience, generation, spawns);
	}

	public static Biome permianConiferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianConiferForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianGinkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianGinkgoForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianWindsweptHills() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianWindsweptHills(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));

		return biome(Precipitation.RAIN, BiomeCategory.EXTREME_HILLS, 0.8F, 0.3F, ambience, generation, spawns);
	}

	public static Biome permianOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianOcean(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome permianWarmOcean() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianCoralReef(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianAshyMedows() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianAshyMedows(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 5.0F, ambience, generation, spawns);
	}

	public static Biome permianDriedPlains() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianDriedPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 2.0F, ambience, generation, spawns);
	}

	public static Biome permianFloodBasalts() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianFloodBasalts(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(4.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.0F, 4.0F, ambience, generation, spawns);
	}

	public static Biome permianPlains() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.0F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianRiver() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianRiver(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.RIVER, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianShore() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianShore(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.BEACH, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianMarsh() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianMarsh(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome permianDripstoneCaves() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianDripstoneCaves(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);

		return biome(Precipitation.RAIN, BiomeCategory.BEACH, 0.8F, 0.7F, music, ambience, generation, spawns);
	}

	public static Biome permianGrove() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianGrove(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(-0.2F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_GROVE);

		return biome(Precipitation.SNOW, BiomeCategory.FOREST, 0.8F, -0.2F, music, ambience, generation, spawns);
	}

	public static Biome permianSnowySlopes() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianSnowySlopes(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(-0.3F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SNOWY_SLOPES);

		return biome(Precipitation.SNOW, BiomeCategory.MOUNTAIN, 0.9F, -0.3F, music, ambience, generation, spawns);
	}

	public static Biome permianFrozenPeaks() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianFrozenPeaks(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(-0.7F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS);

		return biome(Precipitation.SNOW, BiomeCategory.MOUNTAIN, 0.9F, -0.7F, music, ambience, generation, spawns);
	}

	public static Biome permianJaggedPeaks() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianJaggedPeaks(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(-0.7F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS);

		return biome(Precipitation.SNOW, BiomeCategory.MOUNTAIN, 0.9F, -0.7F, music, ambience, generation, spawns);
	}

	public static Biome permianStonyPeaks() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.permianStonyPeaks(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));

		Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS);

		return biome(Precipitation.SNOW, BiomeCategory.MOUNTAIN, 1.0F, 0.3F, music, ambience, generation, spawns);
	}
}
