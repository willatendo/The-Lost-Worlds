package lostworlds.library.biome.biomes.permian.plains;

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

public class PermianDriedPlains extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_DRIED_PLAINS, ModSurfaceBuilders.DRIED_SOIL_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

	static {
		ModBiomeFeatures.permianDriedPlains(GENERATION);
	}

	public PermianDriedPlains(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.NONE, Category.PLAINS, depth, scale, 0.0F, 2.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public PermianDriedPlains() {
		this(0.125F, 0.05F);
	}
}
