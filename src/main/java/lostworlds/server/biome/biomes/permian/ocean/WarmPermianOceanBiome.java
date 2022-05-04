package lostworlds.server.biome.biomes.permian.ocean;

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

public class WarmPermianOceanBiome extends ModBiome {
	private float depth;
	private float scale;

	public WarmPermianOceanBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public WarmPermianOceanBiome() {
		this(-1.0F, 0.1F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(RainType.RAIN, Category.OCEAN, this.depth, this.scale, 0.0F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_PERMIAN_MOUNTAINS.configured(ModSurfaceBuilderConfigs.PERMIAN_STONE_CONFIG.get()));
		ModBiomeFeatures.permianCoralReef(generation);
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
		ambience.waterColor(WARM_OCEAN_WATER_COLOUR).waterFogColor(WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
		return ambience.build();
	}
}
