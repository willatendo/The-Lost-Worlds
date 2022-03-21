package lostworlds.server.biome.biomes.overworld.forest;

import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class ConiferForest extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(LostWorldsSurfaceBuilders.NAKED_JURASSIC_FOREST, ModSurfaceBuilders.MOSSY_SOIL_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = defaultOverworldSpawns();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

	static {
		MOB_SPAWNS.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 5, 4, 4)).setPlayerCanSpawn();

		GENERATION.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

		ModBiomeFeatures.coniferForest(GENERATION);
	}

	public ConiferForest(float depth, float scale) {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.FOREST, depth, scale, 0.8F, 0.7F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}

	public ConiferForest() {
		this(0.1F, 0.2F);
	}
}
