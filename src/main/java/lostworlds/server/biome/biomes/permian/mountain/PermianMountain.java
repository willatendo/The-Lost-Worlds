package lostworlds.server.biome.biomes.permian.mountain;

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

public class PermianMountain extends TyrannoBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(LostWorldsSurfaceBuilders.NAKED_PERMIAN_MOUNTAINS, ModSurfaceBuilders.PERMIAN_STONE_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));

	static {
		ModBiomeFeatures.permianMountains(GENERATION);
		ModBiomeFeatures.permianMountainsSpawns(MOB_SPAWNS);
	}

	public PermianMountain() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.EXTREME_HILLS, 1.0F, 0.5F, 0.0F, 0.2F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}