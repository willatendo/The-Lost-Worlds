package lostworlds.server.biome.biomes.permian.swamp;

import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeSpecialEffects.GrassColorModifier;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class PermianMarshBiome extends ModBiome {
	@Override
	public Biome getBiome() {
		return this.biome(Precipitation.RAIN, BiomeCategory.SWAMP, -0.2F, 0.1F, 0.8F, 0.8F, this.ambience(), this.generation(), this.spawn()).build();
	}

	@Override
	public BiomeGenerationSettings generation() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		generation.surfaceBuilder(() -> LostWorldsSurfaceBuilders.NAKED_PERMIAN_MARSH.configured(ModSurfaceBuilderConfigs.MUD_CONFIG.get()));
		ModBiomeFeatures.permianMarsh(generation);
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
		ambience.waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
		ambience.grassColorModifier(GrassColorModifier.SWAMP);
		return ambience.build();
	}
}
