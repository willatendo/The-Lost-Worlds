package lostworlds.library.biome.biomes.permian.forest;

import lostworlds.library.biome.BiomeMaker;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;

public class PermianConiferForest extends SimpleBiome
{
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration() 
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
		
	}
	
	public static Biome create()
	{
		return BiomeMaker.create(RainType.RAIN, Category.FOREST, 0.125F, 0.05F, 0.2F, 0.0F, 0x3181c6, 0x1c65a5, 0x77d3ea, 0x39aac6, 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
	}
}
