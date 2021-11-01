package lostworlds.library.biome.biomes.permian.plains;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class PermianAshyMedow extends SimpleBiome
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
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_ASHY_MEDOWS_BUILDER);
		
		ModBiomeFeatures.permianAshyMedows(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 5.0F, 0.0F, 0x999999, 0x757575, 0x494949, 0x3a3a3a, 0x686868, 0x686868, MOB_SPAWNS.build(), GENERATION.build());
	}
}
