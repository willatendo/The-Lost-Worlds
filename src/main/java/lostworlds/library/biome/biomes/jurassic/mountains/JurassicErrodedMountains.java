package lostworlds.library.biome.biomes.jurassic.mountains;

import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;

public class JurassicErrodedMountains extends SimpleBiome
{
	public static final MobSpawnInfo.Builder MOB_SPAWNS = defaultOverworldSpawns();
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_JURASSIC_ERRODED_MOUNTAINS, ModSurfaceBuilders.JURASSIC_COBBLESTONE_CONFIG);
	
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration() 
	{	
		ModBiomeFeatures.jurassicMountains(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.EXTREME_HILLS, 1.0F, 0.5F, 0.2F, 0.0F, 0x3181c6, 0x1c65a5, 0x77d3ea, 0x39aac6, 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
	}
}
