package lostworlds.library.biome.biomes.cretaceous.desert;

import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousRedDesert extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_CRETACEOUS_RED_DESERT, ModSurfaceBuilders.RED_TERRACOTTA_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

	static {
		ModBiomeFeatures.cretaceousDesert(GENERATION);
	}

	public CretaceousRedDesert(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.NONE, Category.DESERT, depth, scale, 0.0F, 3.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public CretaceousRedDesert() {
		this(0.125F, 0.05F);
	}
}
