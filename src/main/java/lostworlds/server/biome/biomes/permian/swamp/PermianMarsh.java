package lostworlds.server.biome.biomes.permian.swamp;

import lostworlds.server.biome.BaseBiomeInfo;
import lostworlds.server.biome.ModBiome;
import lostworlds.server.biome.ModBiomeFeatures;
import lostworlds.server.biome.ModSurfaceBuilders;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class PermianMarsh extends ModBiome {
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(LostWorldsSurfaceBuilders.NAKED_PERMIAN_MARSH, ModSurfaceBuilders.MUD_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));

	static {
		ModBiomeFeatures.permianMarsh(GENERATION);
		ModBiomeFeatures.permianMarshSpawns(MOB_SPAWNS);
	}

	public PermianMarsh() {
		super(BaseBiomeInfo.biome(RainType.RAIN, Category.SWAMP, -0.2F, 0.1F, 0.8F, 0.8F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
