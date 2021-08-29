package lostworlds.library.biome.biomes.permian.plains;

import lostworlds.library.biome.BiomeMaker;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;

public class PermianAshyMedow extends SimpleBiome
{
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration() 
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_ASHY_MEDOWS);
		
		ModBiomeFeatures.permianAshyMedows(GENERATION);
	}
	
	public static Biome create()
	{
		return BiomeMaker.create(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 2.0F, 0.0F, 0x999999, 0x757575, 0x494949, 0x3a3a3a, 0x686868, 0x686868, MOB_SPAWNS.build(), GENERATION.build());
	}
}
