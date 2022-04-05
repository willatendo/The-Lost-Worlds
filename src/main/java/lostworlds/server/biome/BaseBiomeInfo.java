package lostworlds.server.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class BaseBiomeInfo {
	// Water Colour
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

	// Fog Colour
	public static final int BASE_FOG_COLOUR = 12638463;

	public static Biome.Builder biome(RainType rain, Category category, float depth, float scale, float downfall, float temperature, BiomeAmbience ambience, BiomeGenerationSettings settings, MobSpawnInfo spawningInfo) {
		return new Biome.Builder().precipitation(rain).biomeCategory(category).depth(depth).scale(scale).downfall(downfall).temperature(temperature).generationSettings(settings).specialEffects(ambience).mobSpawnSettings(spawningInfo);
	}
}
