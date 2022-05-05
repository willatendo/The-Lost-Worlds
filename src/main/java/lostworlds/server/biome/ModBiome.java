package lostworlds.server.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public abstract class ModBiome extends BiomeParts {
	public abstract Biome getBiome();

	public abstract BiomeGenerationSettings generation();

	public abstract MobSpawnInfo spawn();

	public abstract BiomeAmbience ambience();
}
