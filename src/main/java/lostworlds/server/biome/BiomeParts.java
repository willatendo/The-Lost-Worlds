package lostworlds.server.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
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

	public static final Music NORMAL_MUSIC = null;

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

	public static Biome biome(Precipitation precipitation, BiomeCategory category, float downfall, float temperature, BiomeSpecialEffects.Builder effects, BiomeGenerationSettings.Builder settings, MobSpawnSettings.Builder spawningInfo) {
		return biome(precipitation, category, downfall, temperature, NORMAL_MUSIC, effects, settings, spawningInfo);
	}

	public static Biome biome(Precipitation precipitation, BiomeCategory category, float downfall, float temperature, Music music, BiomeSpecialEffects.Builder effects, BiomeGenerationSettings.Builder settings, MobSpawnSettings.Builder spawningInfo) {
		effects.backgroundMusic(music);
		return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(category).downfall(downfall).temperature(temperature).generationSettings(settings.build()).specialEffects(effects.build()).mobSpawnSettings(spawningInfo.build()).build();
	}
}
