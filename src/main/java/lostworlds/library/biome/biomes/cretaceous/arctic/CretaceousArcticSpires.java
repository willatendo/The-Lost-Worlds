package lostworlds.library.biome.biomes.cretaceous.arctic;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousArcticSpires extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.BASE_WATER_COLOUR).waterFogColor(BaseBiomes.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F));
	
	static
	{		
		GENERATION.surfaceBuilder(ModSurfaceBuilders.CRETACEOUS_SNOW_BUILDER);
		
		ModBiomeFeatures.cretaceousArcticSpires(GENERATION);
	}
	
	public CretaceousArcticSpires() 
	{
		super(BaseBiomes.biome(RainType.SNOW, Category.PLAINS, 0.125F, 0.05F, 0.5F, 0.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
