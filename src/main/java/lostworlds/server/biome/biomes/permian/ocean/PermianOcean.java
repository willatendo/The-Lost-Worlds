package lostworlds.server.biome.biomes.permian.ocean;

import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class PermianOcean extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(LostWorldsSurfaceBuilders.NAKED_PERMIAN_OCEAN, ModSurfaceBuilders.SILT_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.OCEAN_WATER_COLOUR).waterFogColor(BaseBiomeInfo.OCEAN_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));

	static {
		ModBiomeFeatures.permianOcean(GENERATION);
		ModBiomeFeatures.permianOceanSpawns(MOB_SPAWNS);
	}

	public PermianOcean(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.FOREST, depth, scale, 0.0F, 0.2F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public PermianOcean() {
		this(-1.0F, 0.1F);
	}
}