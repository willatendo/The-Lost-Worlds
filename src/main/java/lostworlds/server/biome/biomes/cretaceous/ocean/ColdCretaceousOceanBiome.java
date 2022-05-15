package lostworlds.server.biome.biomes.cretaceous.ocean;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class ColdCretaceousOceanBiome extends ModBiome {
	private float depth;
	private float scale;

	public ColdCretaceousOceanBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public ColdCretaceousOceanBiome() {
		this(-1.0F, 0.1F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(RainType.RAIN, Category.OCEAN, this.depth, this.scale, 0.8F, 0.2F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get()));
		ModBiomeFeatures.coldCretaceousOcean(generation);
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
		ambience.waterColor(COLD_OCEAN_WATER_COLOUR).waterFogColor(COLD_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.2F));
		return ambience.build();
	}
}