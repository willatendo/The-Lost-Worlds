package lostworlds.server.biome.biomes.cretaceous.ocean;

import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousOcean extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.OCEAN_WATER_COLOUR).waterFogColor(BaseBiomeInfo.OCEAN_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

	static {
		GENERATION.surfaceBuilder(ModSurfaceBuilders.CRETACEOUS_ROCKY_SOIL_BUILDER);

		ModBiomeFeatures.cretaceousOcean(GENERATION);
	}

	public CretaceousOcean(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.OCEAN, depth, scale, 0.8F, 0.5F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public CretaceousOcean() {
		this(-1.0F, 0.1F);
	}
}