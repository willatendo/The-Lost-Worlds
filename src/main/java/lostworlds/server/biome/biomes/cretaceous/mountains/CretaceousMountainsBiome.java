package lostworlds.server.biome.biomes.cretaceous.mountains;

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

public class CretaceousMountainsBiome extends ModBiome {
	private float depth;
	private float scale;

	public CretaceousMountainsBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public CretaceousMountainsBiome() {
		this(1.0F, 0.5F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(RainType.RAIN, Category.EXTREME_HILLS, this.depth, this.scale, 0.3F, 0.2F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_CRETACEOUS_MOUNTAINS.configured(ModSurfaceBuilderConfigs.STONE_CONFIG.get()));
		ModBiomeFeatures.cretaceousMountains(generation);
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
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.3F));
		return ambience.build();
	}
}
