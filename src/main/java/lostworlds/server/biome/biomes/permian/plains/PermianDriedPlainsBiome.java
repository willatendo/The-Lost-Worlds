package lostworlds.server.biome.biomes.permian.plains;

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

public class PermianDriedPlainsBiome extends ModBiome {
	private float depth;
	private float scale;

	public PermianDriedPlainsBiome(float depth, float scale) {
		this.depth = depth;
		this.scale = scale;
	}

	public PermianDriedPlainsBiome() {
		this(0.125F, 0.05F);
	}

	@Override
	public Biome getBiome() {
		return this.biome(RainType.NONE, Category.PLAINS, this.depth, this.scale, 0.8F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.PERMIAN_DRIED_PLAINS.configured(ModSurfaceBuilderConfigs.DRIED_SOIL_CONFIG.get()));
		ModBiomeFeatures.permianDriedPlains(generation);
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
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
		return ambience.build();
	}
}
