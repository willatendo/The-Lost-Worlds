package lostworlds.server.biome.biomes.cretaceous.arctic;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class CretaceousArctic extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

	static {
		GENERATION.surfaceBuilder(ModSurfaceBuilders.CRETACEOUS_SNOW_BUILDER);

		ModBiomeFeatures.cretaceousArctic(GENERATION);
		ModBiomeFeatures.permianAshyMedowsSpawns(MOB_SPAWNS);
	}

	public CretaceousArctic(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.SNOW, Category.PLAINS, depth, scale, 0.5F, 0.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public CretaceousArctic() {
		this(0.125F, 0.05F);
	}
}
