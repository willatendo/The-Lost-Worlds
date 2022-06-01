package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class CretaceousBiomes extends BiomeParts {
	public static Biome cretaceousArctic() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousArctic(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.0F));

		return biome(Precipitation.SNOW, BiomeCategory.ICY, 0.8F, 0.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousArcticSpires() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousArcticSpires(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.0F));

		return biome(Precipitation.SNOW, BiomeCategory.ICY, 0.8F, 0.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousFrozenForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousFrozenForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.0F));

		return biome(Precipitation.SNOW, BiomeCategory.ICY, 0.8F, 0.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousDesert() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousDesert(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

		return biome(Precipitation.NONE, BiomeCategory.DESERT, 0.0F, 2.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousRedDesert() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousDesert(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.5F));

		return biome(Precipitation.NONE, BiomeCategory.DESERT, 0.0F, 2.5F, ambience, generation, spawns);
	}

	public static Biome cretaceousAraucariaForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousAraucariaForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousConiferForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousConiferForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousGinkgoForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousGinkgoForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousRedwoodsForest() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousRedwoodsForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.FOREST, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome cretaceousWindsweptHills() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousWindsweptHills(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));

		return biome(Precipitation.RAIN, BiomeCategory.EXTREME_HILLS, 0.8F, 0.3F, ambience, generation, spawns);
	}

	public static Biome cretaceousOceanBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousOcean(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.5F, ambience, generation, spawns);
	}

	public static Biome cretaceousWarmOceanBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousCoralReef(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(WARM_OCEAN_WATER_COLOUR).waterFogColor(WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousColdOceanBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.coldCretaceousOcean(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(COLD_OCEAN_WATER_COLOUR).waterFogColor(COLD_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));

		return biome(Precipitation.RAIN, BiomeCategory.OCEAN, 0.8F, 0.3F, ambience, generation, spawns);
	}

	public static Biome cretaceousFloodBasaltsBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousFloodBasalts(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(4.0F));

		return biome(Precipitation.NONE, BiomeCategory.PLAINS, 0.8F, 4.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousGameTrailBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousGameTrail(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(1.5F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.8F, 1.5F, ambience, generation, spawns);
	}

	public static Biome cretaceousMedowBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousMedow(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(1.0F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.8F, 1.0F, ambience, generation, spawns);
	}

	public static Biome cretaceousPlainsBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousPlains(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(1.0F));

		return biome(Precipitation.RAIN, BiomeCategory.PLAINS, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousRiverBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousRiver(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(1.0F));

		return biome(Precipitation.RAIN, BiomeCategory.RIVER, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousShoreBiome() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousShore(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(1.0F));

		return biome(Precipitation.RAIN, BiomeCategory.BEACH, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousMarsh() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousMarsh(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousSwamp() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousSwamp(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousFen() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousFen(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}

	public static Biome cretaceousBog() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		ModBiomeFeatures.cretaceousBog(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

		return biome(Precipitation.RAIN, BiomeCategory.SWAMP, 0.8F, 0.7F, ambience, generation, spawns);
	}
}
