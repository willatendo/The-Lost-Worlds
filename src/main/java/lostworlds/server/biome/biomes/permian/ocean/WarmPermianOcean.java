package lostworlds.server.biome.biomes.permian.ocean;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class WarmPermianOcean extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.WARM_OCEAN_WATER_COLOUR).waterFogColor(BaseBiomeInfo.LUKE_WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.4F));

	static {
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_WARM_OCEAN_BUILDER);

		ModBiomeFeatures.permianCoralReef(GENERATION);
		ModBiomeFeatures.permianCoralReefSpawns(MOB_SPAWNS);
	}

	public WarmPermianOcean(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.FOREST, depth, scale, 0.0F, 0.4F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public WarmPermianOcean() {
		this(-1.0F, 0.1F);
	}
}
