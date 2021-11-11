package lostworlds.library.biome.biomes.cretaceous.plains;

import lostworlds.library.biome.ModBiomeFeatures;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class CretaceousFloodBasalts extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.BASE_WATER_COLOUR).waterFogColor(BaseBiomes.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(5.0F));
	
	static
	{
		GENERATION.surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS);
		
		ModBiomeFeatures.cretaceousFloodBasalts(GENERATION);
	}
	
	public CretaceousFloodBasalts() 
	{
		super(BaseBiomes.biome(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 0.0F, 5.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
