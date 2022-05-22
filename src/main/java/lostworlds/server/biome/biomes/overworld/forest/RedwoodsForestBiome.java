package lostworlds.server.biome.biomes.overworld.forest;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.data.worldgen.SurfaceBuilders;

public class RedwoodsForestBiome extends ModBiome {
	private float depth;
	private float scale;

	public RedwoodsForestBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public RedwoodsForestBiome() {
		this(0.1F, 0.2F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(Precipitation.RAIN, BiomeCategory.FOREST, this.depth, this.scale, 0.8F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> SurfaceBuilders.GRASS);
		ModBiomeFeatures.redwoodsForest(generation);
		return generation.build();
	}

	@Override
	public MobSpawnSettings spawn() {
		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		spawns.setPlayerCanSpawn();
		return spawns.build();
	}

	@Override
	public BiomeSpecialEffects ambience() {
		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
		return ambience.build();
	}
}
