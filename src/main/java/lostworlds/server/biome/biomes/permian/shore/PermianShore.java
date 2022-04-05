package lostworlds.server.biome.biomes.permian.shore;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class PermianShore extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

	static {
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_BUILDER);

		ModBiomeFeatures.permianShore(GENERATION);
		ModBiomeFeatures.permianShoreSpawns(MOB_SPAWNS);
	}

	public PermianShore() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.BEACH, 0.0F, 0.025F, 0.4F, 0.8F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
