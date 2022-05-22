package lostworlds.server.biome.biomes.cretaceous.arctic;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModConfiguredSurfaceBuilders;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class CretaceousArcticBiome extends ModBiome {
	private float depth;
	private float scale;

	public CretaceousArcticBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public CretaceousArcticBiome() {
		this(0.125F, 0.05F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(Precipitation.SNOW, BiomeCategory.PLAINS, this.depth, this.scale, 0.5F, 0.0F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> ModConfiguredSurfaceBuilders.CRETACEOUS_SNOW_BUILDER);
		ModBiomeFeatures.cretaceousArctic(generation);
		return generation.build();
	}

	@Override
	public MobSpawnSettings spawn() {
		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		return spawns.build();
	}

	@Override
	public BiomeSpecialEffects ambience() {
		BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.0F));
		return ambience.build();
	}
}
