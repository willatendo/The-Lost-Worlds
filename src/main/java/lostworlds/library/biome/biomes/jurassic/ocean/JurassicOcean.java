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

public class JurassicOcean extends SimpleBiome
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
		GENERATION.surfaceBuilder(ModSurfaceBuilders.JURASSIC_ROCKY_SOIL_BUILDER);
		
		ModBiomeFeatures.jurassicOcean(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.FOREST, -1.0F, 0.1F, 0.2F, 0.0F, 0x3181c6, 0x1c65a5, 0x77d3ea, calculateSkyColor(0.2F), 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
	}
}
