package lostworlds.server.biome.biomes.permian.plains;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilderConfigs;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class PermianAshyMedow extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).grassColorOverride(0x686868).foliageColorOverride(0x686868).skyColor(0x3a3a3a);

	static {
		GENERATION.surfaceBuilder(() -> SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.VOLCANIC_ASH_CONFIG.get()));

		ModBiomeFeatures.permianAshyMedows(GENERATION);
		ModBiomeFeatures.permianAshyMedowsSpawns(MOB_SPAWNS);
	}

	public PermianAshyMedow() {
		super(BaseBiomeInfo.biome(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 0.8F, 5.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
