package lostworlds.library.biome;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class ModBiomeFeatures 
{	
	//Permian
	public static void permianAshyMedows(BiomeGenerationSettings.Builder builder)
	{
		addScorchedTrees(builder);
		addAshLayer(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
	}
	
	public static void permianConiferForest(BiomeGenerationSettings.Builder builder)
	{
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
	
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SCANT_CONIFER_TREES);
	}
	
	public static void permianDesert(BiomeGenerationSettings.Builder builder)
	{
		addPermianDesertPlants(builder);
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
	}
	
	public static void permianDriedPlains(BiomeGenerationSettings.Builder builder)
	{
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
	}
	
	public static void permianGinkgoForest(BiomeGenerationSettings.Builder builder)
	{
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addGinkgoTrees(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
	}
	
	public static void permianFloodBasalts(BiomeGenerationSettings.Builder builder)
	{
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		
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
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		
		DefaultBiomeFeatures.addSurfaceFreezing(builder);
	}
	
	public static void permianOcean(BiomeGenerationSettings.Builder builder)
	{
		addPermianRock(builder);
		addPermianOres(builder);
		addModUnderwaterCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
	}
	
	public static void permianMarsh(BiomeGenerationSettings.Builder builder)
	{
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CALAMITES_SUCKOWII);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CALAMITIES_TREES);
	}
	
	
	public static void permianRiver(BiomeGenerationSettings.Builder builder)
	{
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
	}
	
	public static void permianCoralReef(BiomeGenerationSettings.Builder builder)
	{
		addPermianRock(builder);
		addPermianOres(builder);
		addModUnderwaterCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_CORAL_REEF);
	}
	
	public static void permianPlains(BiomeGenerationSettings.Builder builder)
	{
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addAlethopteris(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
	}
	
	public static void ginkgoForest(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.GINKGO_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.GINKGO_FOREST_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BIRCH_OTHER);

		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		
		DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
		DefaultBiomeFeatures.addDefaultCarvers(builder);
		DefaultBiomeFeatures.addDefaultLakes(builder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
		DefaultBiomeFeatures.addForestFlowers(builder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultSoftDisks(builder);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addForestGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultExtraVegetation(builder);
		DefaultBiomeFeatures.addDefaultSprings(builder);
		DefaultBiomeFeatures.addSurfaceFreezing(builder);
	}
	
	//Jurassic
	public static void jurassicConiferForest(BiomeGenerationSettings.Builder builder)
	{
		addConiferTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
	}
	
	public static void jurassicGinkgoForest(BiomeGenerationSettings.Builder builder)
	{
		addGinkgoTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
	}
	
	public static void jurassicAraucariaForest(BiomeGenerationSettings.Builder builder)
	{
		addAraucariaTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
	}
	
	public static void jurassicPlains(BiomeGenerationSettings.Builder builder)
	{
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
	}
	
	public static void jurassicMountains(BiomeGenerationSettings.Builder builder)
	{
		addJurassicRock(builder);
		addJurassicOres(builder);
		addJurassicEmerald(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);

		DefaultBiomeFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_CONIFER_TREES);
	}
	
	public static void jurassicVolcanicRange(BiomeGenerationSettings.Builder builder)
	{
		addJurassicOres(builder);
		addJurassicEmerald(builder);
		addExtraJurassicDiamonds(builder);
		addJurassicUndergroundVariety(builder);
		addDuisbergia(builder);
		addWilliamsonia(builder);
		
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_MAGMA);
	}
	
	public static void jurassicDesert(BiomeGenerationSettings.Builder builder)
	{
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);
	}
	
	public static void jurassicRedwoods(BiomeGenerationSettings.Builder builder)
	{
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPARSE_SEQUOIA_TREES);
	}
	
	public static void jurassicSwamp(BiomeGenerationSettings.Builder builder)
	{
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);

		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CALAMITES_SUCKOWII);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CALAMITIES_TREES);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CYPRESS_TREE);
	}
	
	//Permian Features
	private static void addPermianUndergroundVariety(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_DIRT_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_GRAVEL_ORE);
		
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_LATERLITE_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_LIMESTONE_ORE);
	}
	
	private static void addPermianDesertPlants(BiomeGenerationSettings.Builder builder) 
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_SHRUB_PATCH);
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PERMIAN_DESERT_FERNS_PATCH);
	}
	
	private static void addFernGroundClutter(BiomeGenerationSettings.Builder builder) 
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
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_COPPER_ORE);
	}
	
	private static void addPermianEmerald(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PERMIAN_EMERALD_ORE);
	}
	
	private static void addPermianLakes(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.LAKES, ModConfiguredFeatures.PERMIAN_WATER_LAKE);
		builder.addFeature(GenerationStage.Decoration.LAKES, ModConfiguredFeatures.PERMIAN_LAVA_LAKE);
	}
	
	//Jurassic Features
	private static void addJurassicUndergroundVariety(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_DIRT_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_GRAVEL_ORE);
		
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_LATERLITE_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_LIMESTONE_ORE);
	}
	
	private static void addJurassicRock(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModConfiguredFeatures.JURASSIC_ROCK);
	}
	
	private static void addJurassicOres(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_COAL_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_DIAMOND_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_GOLD_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_IRON_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_LAPIS_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_REDSTONE_ORE);
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_COPPER_ORE);
	}
	
	private static void addJurassicEmerald(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_EMERALD_ORE);
	}
	
	private static void addExtraJurassicDiamonds(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.JURASSIC_DIAMOND_ORE);
	}

	//General Features
	private static void addModCarvers(BiomeGenerationSettings.Builder builder)
	{
		builder.addCarver(GenerationStage.Carving.AIR, ModConfiguredCarvers.CAVE_CARVER);
		builder.addCarver(GenerationStage.Carving.AIR, ModConfiguredCarvers.CANYON_CARVER);
	}
	
	private static void addModUnderwaterCarvers(BiomeGenerationSettings.Builder builder)
	{
		builder.addCarver(GenerationStage.Carving.LIQUID, ModConfiguredCarvers.UNDERWATER_CAVE_CARVER);
		builder.addCarver(GenerationStage.Carving.LIQUID, ModConfiguredCarvers.UNDERWATER_CANYON_CARVER);
	}
	
	private static void addSpring(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ANCIENT_SPRING);
	}
	
	public static void addAraucariaTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ARAUCARIA_TREES);
	}
	
	public static void addConiferTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.CONIFER_TREES);
	}
	
	private static void addGinkgoTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.GINKGO_TREES);
	}
	
	private static void addScorchedTrees(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SINGLE_SCORCHED_TREE);
	}
	
	private static void addAshLayer(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.ASH_LAYER);
	}

	private static void addAlethopteris(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ALETHOPTERIS_PATCH);
	}

	private static void addDuisbergia(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.DUISBERGIA_PATCH);
	}

	private static void addWilliamsonia(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.WILLIAMSONIA_PATCH);
	}

	private static void addZamites(BiomeGenerationSettings.Builder builder)
	{
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.ZAMITES);
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
