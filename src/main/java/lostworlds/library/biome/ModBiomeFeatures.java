package lostworlds.library.biome;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.feature.Features;

public class ModBiomeFeatures 
{	
	//Permian
	public static void permianAshyMedows(BiomeGenerationSettings.Builder builder)
	{
		addScorchedTrees(builder);
		addAshyShrub(builder);
		addAshLayer(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
	}
	
	public static void permianConiferForest(BiomeGenerationSettings.Builder builder)
	{
		addPermianGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}
	
	public static void permianDesert(BiomeGenerationSettings.Builder builder)
	{
		addPermianDesertPlants(builder);
		addPermianGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
	}
	
	public static void permianDriedPlains(BiomeGenerationSettings.Builder builder)
	{
		addPermianGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}
	
	public static void permianGinkgoForest(BiomeGenerationSettings.Builder builder)
	{
		addPermianGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addGinkgoTrees(builder);
		addPermianCarvers(builder);
	}
	
	public static void permianFloodBasalts(BiomeGenerationSettings.Builder builder)
	{
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA);
		builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.BASALT_PILLAR);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_MAGMA);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.DELTA);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA_DOUBLE);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.LARGE_BASALT_COLUMNS);
	}
	
	public static void permianMountains(BiomeGenerationSettings.Builder builder)
	{
		addConiferTrees(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianEmerald(builder);
		addPermianCarvers(builder);
		
		DefaultBiomeFeatures.addSurfaceFreezing(builder);
	}
	
	public static void permianOcean(BiomeGenerationSettings.Builder builder)
	{
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianUnderwaterCarvers(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
	}
	
	public static void permianRiver(BiomeGenerationSettings.Builder builder)
	{
		addPermianOres(builder);
		addPermianCarvers(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
	}
	
	public static void permianCoralReef(BiomeGenerationSettings.Builder builder)
	{
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianUnderwaterCarvers(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_CORAL_REEF);
	}
	
	public static void permianPlains(BiomeGenerationSettings.Builder builder)
	{
		addPermianGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianCarvers(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}
	
	//Permian Features
	private static void addPermianDesertPlants(BiomeGenerationSettings.Builder builder) 
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_SHRUB_PATCH);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_FERNS_PATCH);
	}
	
	private static void addPermianGroundClutter(BiomeGenerationSettings.Builder builder) 
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FERN_PATCH);
	}
	
	private static void addPermianRock(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModConfiguredFeatures.PERMIAN_ROCK);
	}
	
	private static void addPermianOres(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_COAL_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_DIAMOND_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_GOLD_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_IRON_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_LAPIS_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_REDSTONE_ORE);
	}
	
	private static void addPermianEmerald(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_EMERALD_ORE);
	}
	
	private static void addPermianCarvers(BiomeGenerationSettings.Builder builder)
	{
		builder.addCarver(Carving.AIR, ModConfiguredCarvers.PERMIAN_CAVE_CARVER);
		builder.addCarver(Carving.AIR, ModConfiguredCarvers.PERMIAN_CANYON_CARVER);
	}
	
	private static void addPermianUnderwaterCarvers(BiomeGenerationSettings.Builder builder)
	{
		builder.addCarver(Carving.LIQUID, ModConfiguredCarvers.PERMIAN_UNDERWATER_CAVE_CARVER);
		builder.addCarver(Carving.LIQUID, ModConfiguredCarvers.PERMIAN_UNDERWATER_CANYON_CARVER);
	}

	//General Features
	private static void addConiferTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_STICKS);
	}
	
	private static void addGinkgoTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.GINKGO_TREES);
	}
	
	private static void addScorchedTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SINGLE_SCORCHED_TREE);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SCORCHED_STICKS);
	}
	
	private static void addAshLayer(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.ASH_LAYER);
	}

	private static void addAshyShrub(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ASHY_SHRUB);
	}
	
	private static void addBasaltDiamondOre(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.BASALT_DIAMOND_ORE);
	}
	
	private static void addGeyser(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.GEYSER_BLOCK);
	}
}
