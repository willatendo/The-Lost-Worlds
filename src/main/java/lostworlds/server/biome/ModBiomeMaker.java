package lostworlds.server.biome;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;

public class ModBiomeMaker {
	public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, int grassColour, int foliageColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation) {
		return (new Biome.Builder()).precipitation(rainType).biomeCategory(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).specialEffects((new BiomeAmbience.Builder().waterColor(waterColour).waterFogColor(waterFogColour).fogColor(fogColour).skyColor(skyColour).grassColorOverride(grassColour).foliageColorOverride(foliageColour).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)).build()).mobSpawnSettings(mobSpawns).generationSettings(generation).build();
	}

	public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation) {
		return (new Biome.Builder()).precipitation(rainType).biomeCategory(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).specialEffects((new BiomeAmbience.Builder().waterColor(waterColour).waterFogColor(waterFogColour).fogColor(fogColour).skyColor(skyColour).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)).build()).mobSpawnSettings(mobSpawns).generationSettings(generation).build();
	}

	public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation) {
		return (new Biome.Builder()).precipitation(rainType).biomeCategory(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).specialEffects((new BiomeAmbience.Builder().waterColor(waterColour).waterFogColor(waterFogColour).fogColor(fogColour).skyColor(ModBiome.calculateSkyColor(temperature)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)).build()).mobSpawnSettings(mobSpawns).generationSettings(generation).build();
	}

	public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, int grassColour, int foliageColour, float particleAmount, BasicParticleType particle, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation) {
		return (new Biome.Builder()).precipitation(rainType).biomeCategory(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).specialEffects((new BiomeAmbience.Builder().waterColor(waterColour).waterFogColor(waterFogColour).fogColor(fogColour).skyColor(skyColour).grassColorOverride(grassColour).foliageColorOverride(foliageColour).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)).ambientParticle(new ParticleEffectAmbience(particle, particleAmount)).build()).mobSpawnSettings(mobSpawns).generationSettings(generation).build();
	}
}
