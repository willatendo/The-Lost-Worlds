package lostworlds.library.biome.biomes.jurassic.plains;

import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class JurassicPlains extends SimpleBiome
{
	public static final MobSpawnInfo.Builder MOB_SPAWNS = defaultOverworldSpawns();
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_JURASSIC_PLAINS, ModSurfaceBuilders.MOSSY_SOIL_CONFIG);
	
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration() 
	{
		ModBiomeFeatures.jurassicPlains(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.PLAINS, 0.1F, 0.2F, 0.7F, 0.8F, 4159204, 329011, 12638463, calculateSkyColor(0.7F), MOB_SPAWNS.build(), GENERATION.build());
	}
	
	public static Biome create(float depth, float scale)
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.PLAINS, depth, scale, 0.7F, 0.8F, 4159204, 329011, 12638463, calculateSkyColor(0.7F), MOB_SPAWNS.build(), GENERATION.build());
	}
}