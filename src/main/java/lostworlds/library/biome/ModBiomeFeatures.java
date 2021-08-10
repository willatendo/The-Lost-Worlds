package lostworlds.library.biome;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class ModBiomeFeatures 
{	
	//Collected Features
	public static void permianDesert(BiomeGenerationSettings.Builder builder)
	{
		addPermianDesertPlants(builder);
		addGroundClutter(builder);
	}
	
	public static void permianDriedPlains(BiomeGenerationSettings.Builder builder)
	{
		addGroundClutter(builder);
		addConiferTrees(builder);
	}
	
	public static void permianAshyMedows(BiomeGenerationSettings.Builder builder)
	{
		addScorchedTrees(builder);
		addAshyShrub(builder);
		addAshLayer(builder);
	}
	
	public static void permianFloodBasalts(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA);
		builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.BASALT_PILLAR);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_MAGMA);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.DELTA);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA_DOUBLE);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.LARGE_BASALT_COLUMNS);
		addBasaltDiamondOre(builder);
		addGeyser(builder);
	}
	
	public static void permianMountains(BiomeGenerationSettings.Builder builder)
	{
		addConiferTrees(builder);
		
		DefaultBiomeFeatures.addSurfaceFreezing(builder);
	}
	
	public static void permianConiferForest(BiomeGenerationSettings.Builder builder)
	{
		addGroundClutter(builder);
		addConiferTrees(builder);
	}
	
	public static void permianGinkgoForest(BiomeGenerationSettings.Builder builder)
	{
		addGroundClutter(builder);
		addGinkgoTrees(builder);
	}
	
	public static void permianPlains(BiomeGenerationSettings.Builder builder)
	{
		addGroundClutter(builder);
	}
	
	public static void permianOcean(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
	}
	
	//Features
	private static void addPermianDesertPlants(BiomeGenerationSettings.Builder builder) 
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_SHRUB_PATCH);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_FERNS_PATCH);
	}
	
	private static void addGroundClutter(BiomeGenerationSettings.Builder builder) 
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FERN_PATCH);
	}
	
	private static void addConiferTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.TREES_CONIFER);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}
	
	private static void addGinkgoTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.TREES_GINKGO);
		//builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}

	private static void addAshyShrub(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ASHY_SHRUB);
	}
	
	private static void addAshLayer(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.ADD_ASH_LAYER);
	}
	
	private static void addScorchedTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SINGLE_SCORCHED_TREE);
	}
	
	private static void addBasaltDiamondOre(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.ORE_BASALT_DIAMOND);
	}
	
	private static void addGeyser(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.GEYSER_BLOCK);
	}
}
