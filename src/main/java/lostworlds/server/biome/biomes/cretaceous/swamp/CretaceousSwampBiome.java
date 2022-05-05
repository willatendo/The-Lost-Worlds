package lostworlds.server.biome.biomes.cretaceous.swamp;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeAmbience.GrassColorModifier;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class CretaceousSwampBiome extends ModBiome {
	@Override
	public Biome getBiome() {
		return this.biome(RainType.RAIN, Category.SWAMP, -0.2F, 0.1F, 0.8F, 0.7F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_CRETACEOUS_SWAMP.configured(ModSurfaceBuilderConfigs.MUD_CONFIG.get()));
		ModBiomeFeatures.cretaceousMarsh(generation);
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
		ambience.grassColorModifier(GrassColorModifier.SWAMP);
		return ambience.build();
	}
}
