package lostworlds.library.biome.biomes.jurassic.swamp;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class JurassicBog extends SimpleBiome
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
		GENERATION.surfaceBuilder(ModSurfaceBuilders.JURASSIC_PODZOL_BUILDER);

		ModBiomeFeatures.jurassicBog(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.SWAMP, -0.2F, 0.1F, 0.7F, 0.8F, 4159204, 329011, 12638463, calculateSkyColor(0.7F), MOB_SPAWNS.build(), GENERATION.build());
	}
}
