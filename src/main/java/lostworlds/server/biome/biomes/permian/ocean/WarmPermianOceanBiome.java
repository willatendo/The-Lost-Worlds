package lostworlds.server.biome.biomes.permian.ocean;

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
		return this.biome(Precipitation.RAIN, BiomeCategory.OCEAN, this.depth, this.scale, 0.0F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_PERMIAN_MOUNTAINS.configured(ModSurfaceBuilderConfigs.PERMIAN_STONE_CONFIG.get()));
		ModBiomeFeatures.permianCoralReef(generation);
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
		ambience.waterColor(WARM_OCEAN_WATER_COLOUR).waterFogColor(WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
		return ambience.build();
	}
}
