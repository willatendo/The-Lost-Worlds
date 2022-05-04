package lostworlds.server.biome.biomes.cretaceous.forest;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class CretaceousConiferForestBiome extends ModBiome {
	private float depth;
	private float scale;

	public CretaceousConiferForestBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public CretaceousConiferForestBiome() {
		this(0.1F, 0.2F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(RainType.RAIN, Category.FOREST, this.depth, this.scale, 0.8F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_CRETACEOUS_FOREST.configured(ModSurfaceBuilderConfigs.COARSE_DIRT.get()));
		ModBiomeFeatures.cretaceousConiferForest(generation);
		return generation.build();
	}

	@Override
	public MobSpawnInfo spawn() {
		MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
		return spawns.build();
	}

	@Override
	public BiomeAmbience ambience() {
		BiomeAmbience.Builder ambience = new BiomeAmbience.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.8F));
		return ambience.build();
	}
}
