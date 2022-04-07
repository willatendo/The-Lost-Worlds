package lostworlds.server.biome.biomes.jurassic.desert;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class JurassicDesert extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));

	static {
		GENERATION.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_JURASSIC_DESERT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get()));

		ModBiomeFeatures.jurassicDesert(GENERATION);
	}

	public JurassicDesert(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.NONE, Category.DESERT, depth, scale, 0.0F, 2.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public JurassicDesert() {
		this(0.1F, 0.2F);
	}
}
