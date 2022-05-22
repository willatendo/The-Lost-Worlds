package lostworlds.server.biome.biomes.cretaceous.desert;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class CretaceousDesertBiome extends ModBiome {
	private float depth;
	private float scale;

	public CretaceousDesertBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public CretaceousDesertBiome() {
		this(0.125F, 0.05F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(Precipitation.NONE, BiomeCategory.DESERT, this.depth, this.scale, 0.0F, 2.0F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_CRETACEOUS_DESERT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get()));
		ModBiomeFeatures.cretaceousDesert(generation);
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
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));
		return ambience.build();
	}
}
