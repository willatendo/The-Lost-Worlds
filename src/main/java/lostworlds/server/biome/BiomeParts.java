package lostworlds.server.biome;

import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class BiomeParts {
	public static final int BASE_WATER_COLOUR = 4159204;
	public static final int BASE_WATER_FOG_COLOUR = 329011;

	public static final int OCEAN_WATER_COLOUR = 4159204;
	public static final int OCEAN_WATER_FOG_COLOUR = 4159204;

	public static final int LUKE_WARM_OCEAN_WATER_COLOUR = 4566514;
	public static final int LUKE_WARM_OCEAN_WATER_FOG_COLOUR = 267827;

	public static final int WARM_OCEAN_WATER_COLOUR = 4445678;
	public static final int WARM_OCEAN_WATER_FOG_COLOUR = 270131;

	public static final int COLD_OCEAN_WATER_COLOUR = 4020182;
	public static final int COLD_OCEAN_WATER_FOG_COLOUR = 329011;

	public static final int BASE_FOG_COLOUR = 12638463;

	public static int calculateSkyColor(float temperature) {
		float colour = temperature / 3.0F;
		colour = Mth.clamp(colour, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - colour * 0.05F, 0.5F + colour * 0.1F, 1.0F);
	}

	public static MobSpawnSettings.Builder defaultOverworldSpawns() {
		MobSpawnSettings.Builder mobspawninfo$builder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.farmAnimals(mobspawninfo$builder);
		BiomeDefaultFeatures.commonSpawns(mobspawninfo$builder);
		return mobspawninfo$builder;
	}

	public Biome.BiomeBuilder biome(Precipitation rain, BiomeCategory category, float depth, float scale, float downfall, float temperature, BiomeSpecialEffects ambience, BiomeGenerationSettings settings, MobSpawnSettings spawningInfo) {
		return new Biome.BiomeBuilder().precipitation(rain).biomeCategory(category).depth(depth).scale(scale).downfall(downfall).temperature(temperature).generationSettings(settings).specialEffects(ambience).mobSpawnSettings(spawningInfo);
	}
}
