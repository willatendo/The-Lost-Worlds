package lostworlds.server.biome.biomes.jurassic.river;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class JurassicRiver extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

	static {
		GENERATION.surfaceBuilder(() -> SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get()));

		ModBiomeFeatures.jurassicRiver(GENERATION);
	}

	public JurassicRiver() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.FOREST, -0.5F, 0.0F, 0.8F, 0.7F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
