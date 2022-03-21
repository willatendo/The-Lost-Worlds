package lostworlds.server.biome.biomes.cretaceous.mountains;

import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousMountains extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(LostWorldsSurfaceBuilders.NAKED_CRETACEOUS_MOUNTAINS, ModSurfaceBuilders.STONE_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));

	static {
		ModBiomeFeatures.cretaceousMountains(GENERATION);
	}

	public CretaceousMountains() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.EXTREME_HILLS, 1.0F, 0.5F, 0.3F, 0.2F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
