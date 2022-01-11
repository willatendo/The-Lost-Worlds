package lostworlds.library.biome.biomes.cretaceous.plains;

import lostworlds.library.biome.ModBiomeFeatures;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousFloodBasalts extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F)).ambientParticle(new ParticleEffectAmbience(ParticleTypes.ASH, 0.0625F));

	static {
		GENERATION.surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS);

		ModBiomeFeatures.cretaceousFloodBasalts(GENERATION);
	}

	public CretaceousFloodBasalts() {
		super(BaseBiomeInfo.biome(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 0.0F, 5.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
