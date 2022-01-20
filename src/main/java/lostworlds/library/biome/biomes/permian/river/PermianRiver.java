package lostworlds.library.biome.biomes.permian.river;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class PermianRiver extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));

	static {
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_BUILDER);

		ModBiomeFeatures.permianRiver(GENERATION);
		ModBiomeFeatures.permianRiverSpawns(MOB_SPAWNS);
	}

	public PermianRiver() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.RIVER, -0.5F, 0.0F, 0.0F, 0.2F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
