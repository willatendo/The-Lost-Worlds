package lostworlds.library.biome.biomes.cretaceous.mountains;

import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousErrodedMountains extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_CRETACEOUS_ERRODED_MOUNTAINS, ModSurfaceBuilders.COBBLESTONE_CONFIG);
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.BASE_WATER_COLOUR).waterFogColor(BaseBiomes.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.7F));
	
	static
	{
		ModBiomeFeatures.cretaceousMountains(GENERATION);
	}
	
	public CretaceousErrodedMountains() 
	{
		super(BaseBiomes.biome(RainType.RAIN, Category.EXTREME_HILLS, 1.0F, 0.5F, 0.3F, 0.2F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}