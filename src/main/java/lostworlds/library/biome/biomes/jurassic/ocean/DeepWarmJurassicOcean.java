package lostworlds.library.biome.biomes.jurassic.ocean;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class DeepWarmJurassicOcean extends SimpleBiome
{
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration() 
	{			
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_WARM_OCEAN_BUILDER);
		
		ModBiomeFeatures.jurassicCoralReef(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.OCEAN, -1.8F, 0.1F, 0.4F, 0.0F, 4566514, 267827, 0x77d3ea, calculateSkyColor(0.4F), 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
	}
}
