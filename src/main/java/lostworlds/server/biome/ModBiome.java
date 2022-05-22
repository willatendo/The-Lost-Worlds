package lostworlds.server.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

public abstract class ModBiome extends BiomeParts {
	public abstract Biome getBiome();

	public abstract BiomeGenerationSettings generation();

	public abstract MobSpawnSettings spawn();

	public abstract BiomeSpecialEffects ambience();
}
