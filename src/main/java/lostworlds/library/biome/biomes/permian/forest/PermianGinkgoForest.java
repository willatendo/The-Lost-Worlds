package lostworlds.library.biome.biomes.permian.forest;

import lostworlds.library.biome.BiomeMaker;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModConfiguredCarvers;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.GenerationStage.Carving;

public class PermianGinkgoForest extends SimpleBiome
{	
	static 
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration()
	{
		ModBiomeFeatures.permianGinkgoForest(GENERATION);
		
		GENERATION.addCarver(Carving.AIR, ModConfiguredCarvers.PERMIAN_CAVE_CARVER);
		GENERATION.addCarver(Carving.AIR, ModConfiguredCarvers.PERMIAN_CANYON_CARVER);
		
		GENERATION.surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
	}
	
	public static Biome create()
	{
		return BiomeMaker.create(RainType.RAIN, Category.FOREST, 0.125F, 0.05F, 0.2F, 0.0F, 0x3181c6, 0x1c65a5, 0x77d3ea, 0x39aac6, 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
	}
}
