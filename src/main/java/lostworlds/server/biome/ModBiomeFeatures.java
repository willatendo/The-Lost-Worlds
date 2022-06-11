package lostworlds.server.biome;

import lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures;
import lostworlds.server.biome.features.placed.PlacedOreFeatures;
import lostworlds.server.biome.features.placed.PlacedPlantPatchFeatures;
import lostworlds.server.biome.features.placed.PlacedTreePatchFeatures;
import lostworlds.server.biome.features.placed.PlacedWaterFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeFeatures {
	// Permian
	public static void permianAshyMedows(BiomeGenerationSettings.Builder builder) {
		addScorchedTrees(builder);
		addAshLayer(builder);
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);
	}

	public static void permianConiferForest(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
	}

	public static void permianDesert(BiomeGenerationSettings.Builder builder) {
		addPermianDesertPlants(builder);
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);
	}

	public static void permianDriedPlains(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void permianGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addGinkgoTrees(builder);
		addUniversalPermianGeneration(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void permianFloodBasalts(BiomeGenerationSettings.Builder builder) {
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addUniversalPermianGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA);
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NetherPlacements.BASALT_BLOBS);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, PlacedOreFeatures.PERMIAN_MAGMA_ORE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.DELTA);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.LARGE_BASALT_COLUMNS);
	}

	public static void permianWindsweptHills(BiomeGenerationSettings.Builder builder) {
		addConiferTrees(builder);
		addPermianRock(builder);
		addPermianEmerald(builder);
		addUniversalPermianGeneration(builder);
	}

	public static void permianOcean(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);
	}

	public static void permianMarsh(BiomeGenerationSettings.Builder builder) {
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.CALAMITES_SUCKOWII);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.CALAMITES_TREES);
	}

	public static void permianRiver(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
	}

	public static void permianShore(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
	}

	public static void permianDripstoneCaves(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);

		BiomeDefaultFeatures.addDripstone(builder);
	}

	public static void permianGrove(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);
		addPermianEmerald(builder);
		addConiferTrees(builder);
	}

	public static void permianSnowySlopes(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);
		addPermianEmerald(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void permianFrozenPeaks(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);
		addPermianEmerald(builder);
	}

	public static void permianJaggedPeaks(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);
		addPermianEmerald(builder);
	}

	public static void permianStonyPeaks(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);
		addPermianEmerald(builder);
	}

	public static void permianCoralReef(BiomeGenerationSettings.Builder builder) {
		addUniversalPermianGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION);
	}

	public static void permianPlains(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addUniversalPermianGeneration(builder);
		addAlethopteris(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	// Jurassic
	public static void jurassicConiferForest(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addConiferTrees(builder);
		addJurassicRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
	}

	public static void jurassicGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addGinkgoTrees(builder);
		addJurassicRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicAraucariaForest(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addAraucariaTrees(builder);
		addJurassicRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
	}

	public static void jurassicPlains(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addJurassicRock(builder);
		addFernGroundClutter(builder);
		addAlethopteris(builder);
		addOsmunda(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicWindsweptHills(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addJurassicRock(builder);
		addJurassicEmerald(builder);
		addFernGroundClutter(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addDicksonia(builder);
		addOsmunda(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicVolcanicRange(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
		addJurassicEmerald(builder);
		addExtraJurassicDiamonds(builder);
		addGeyser(builder);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_MAGMA_ORE);
	}

	public static void jurassicDesert(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
	}

	public static void jurassicRedwoodsForest(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_SEQUOIA_TREES);
	}

	public static void jurassicMarsh(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addZamites(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void jurassicSwamp(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CYPRESS_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CYPRESS_TREES);
	}

	public static void jurassicBog(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
	}

	public static void jurassicFen(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addUniversalJurassicGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
	}

	public static void jurassicOcean(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);
	}

	public static void jurassicCoralReef(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION);
	}

	public static void jurassicRiver(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
	}

	public static void jurassicShore(BiomeGenerationSettings.Builder builder) {
		addUniversalJurassicGeneration(builder);
	}

	// Cretaceous
	public static void cretaceousConiferForest(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
		addConiferTrees(builder);
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
	}

	public static void cretaceousGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
		addGinkgoTrees(builder);
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousAraucariaForest(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
		addAraucariaTrees(builder);
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addDicksonia(builder);
	}

	public static void cretaceousRedwoodsForest(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addUniversalCretaceousGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_SEQUOIA_TREES);
	}

	public static void cretaceousGameTrail(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_BROKEN_TREES);
	}

	public static void cretaceousMedow(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.ARCHAEFRUTUS_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.EUDICOTS_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.MAGNOLIA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousPlains(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addDicksonia(builder);
		addOsmunda(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousWindsweptHills(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
		addCretaceousRock(builder);
		addCretaceousEmerald(builder);
		addFernGroundClutter(builder);
		addAlethopteris(builder);
		addOsmunda(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousArctic(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_FROZEN_TREES);
	}

	public static void cretaceousArcticSpires(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.ICE_SPIKE);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_FROZEN_TREES);
	}

	public static void cretaceousFrozenForest(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_ARCTIC_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_FROZEN_TREES);
	}

	public static void cretaceousDesert(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addFernGroundClutter(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_BROKEN_TREES);
	}

	public static void cretaceousFloodBasalts(BiomeGenerationSettings.Builder builder) {
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA);
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NetherPlacements.BASALT_BLOBS);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, PlacedOreFeatures.CRETACEOUS_MAGMA_ORE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.DELTA);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.LARGE_BASALT_COLUMNS);
	}

	public static void cretaceousMarsh(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addUniversalCretaceousGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addZamites(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void cretaceousSwamp(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addUniversalCretaceousGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CYPRESS_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void cretaceousBog(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addUniversalCretaceousGeneration(builder);
		addFernGroundClutter(builder);

		BiomeDefaultFeatures.addLightBambooVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
	}

	public static void cretaceousFen(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addUniversalCretaceousGeneration(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		BiomeDefaultFeatures.addLightBambooVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SCANT_CONIFER_TREES);
	}

	public static void coldCretaceousOcean(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_COLD);
	}

	public static void cretaceousOcean(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);
	}

	public static void cretaceousCoralReef(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsPlacedFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION);
	}

	public static void cretaceousShore(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
	}

	public static void cretaceousRiver(BiomeGenerationSettings.Builder builder) {
		addUniversalCretaceousGeneration(builder);
	}

	// Overworld
	public static void araucariaForest(BiomeGenerationSettings.Builder builder) {
		addAraucariaTrees(builder);
		globalOverworldGeneration(builder);

		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
	}

	public static void coniferForest(BiomeGenerationSettings.Builder builder) {
		addConiferTrees(builder);
		globalOverworldGeneration(builder);

		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
	}

	public static void ginkgoForest(BiomeGenerationSettings.Builder builder) {
		globalOverworldGeneration(builder);

		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.GINKGO_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_BIRCH_AND_OAK);
	}

	public static void redwoodsForest(BiomeGenerationSettings.Builder builder) {
		addConiferTrees(builder);
		addSequoiaTrees(builder);
		globalOverworldGeneration(builder);

		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
	}

	public static void volcano(BiomeGenerationSettings.Builder builder) {
		addAshLayer(builder);
		addScorchedTrees(builder);
		globalOverworldGeneration(builder);

		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
	}

	private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
		BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
	}

	// Permian Features
	private static void addUniversalPermianGeneration(BiomeGenerationSettings.Builder builder) {
		addDefaultCarversAndLakes(builder);
		addSpring(builder);
		addPermianUndergroundVariety(builder);
		addPermianOres(builder);
		addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavePlacements.AMETHYST_GEODE);
	}

	private static void addPermianUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_DIRT_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_GRAVEL_ORE);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_LIMESTONE_ORE);
	}

	private static void addPermianDesertPlants(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.PERMIAN_DESERT_SHRUB_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.PERMIAN_DESERT_FERNS_PATCH);
	}

	private static void addFernGroundClutter(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_LARGE_FERN);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.FERN_PATCH);
	}

	private static void addPermianRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsPlacedFeatures.PERMIAN_ROCK);
	}

	private static void addPermianOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_COAL_ORE_UPPER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_COAL_ORE_LOWER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_IRON_ORE_UPPER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_IRON_ORE_MIDDLE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_IRON_ORE_SMALL);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_GOLD_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_GOLD_ORE_LOWER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_DIAMOND_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_DIAMOND_ORE_LARGE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_DIAMOND_ORE_BURIED);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_LAPIS_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_LAPIS_ORE_BURIED);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_REDSTONE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_REDSTONE_ORE_LOWER);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_COPPER_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_COPPER_ORE_LARGE);
	}

	private static void addPermianEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PERMIAN_EMERALD_ORE);
	}

	// Jurassic Features
	private static void addUniversalJurassicGeneration(BiomeGenerationSettings.Builder builder) {
		addDefaultCarversAndLakes(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addJurassicOres(builder);
		addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavePlacements.AMETHYST_GEODE);
	}

	private static void addJurassicUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_DIRT_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_GRAVEL_ORE);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_LIMESTONE_ORE);
	}

	private static void addJurassicRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsPlacedFeatures.JURASSIC_ROCK);
	}

	private static void addJurassicOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_COAL_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_DIAMOND_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_GOLD_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_IRON_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_LAPIS_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_REDSTONE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_COPPER_ORE);
	}

	private static void addJurassicEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_EMERALD_ORE);
	}

	private static void addExtraJurassicDiamonds(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.JURASSIC_DIAMOND_ORE);
	}

	// Cretaceous Features
	private static void addUniversalCretaceousGeneration(BiomeGenerationSettings.Builder builder) {
		addDefaultCarversAndLakes(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addCretaceousOres(builder);
		addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavePlacements.AMETHYST_GEODE);
	}

	private static void addCretaceousUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_DIRT_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_GRAVEL_ORE);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_LIMESTONE_ORE);
	}

	private static void addCretaceousRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsPlacedFeatures.CRETACEOUS_ROCK);
	}

	private static void addCretaceousOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_COAL_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_DIAMOND_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_GOLD_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_IRON_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_LAPIS_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_REDSTONE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_COPPER_ORE);
	}

	private static void addCretaceousEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.CRETACEOUS_EMERALD_ORE);
	}

	// General Features
	public static void addDefaultCarversAndLakes(BiomeGenerationSettings.Builder builder) {
		builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
		builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
		builder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
		builder.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);
		builder.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_SURFACE);
	}

	public static void addSurfaceFreezing(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, MiscOverworldPlacements.FREEZE_TOP_LAYER);
	}

	private static void addSpring(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedWaterFeatures.ANCIENT_SPRING);
	}

	public static void addAraucariaTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.ARAUCARIA_TREES);
	}

	public static void addConiferTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.CONIFER_TREES);
	}

	private static void addGinkgoTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.GINKGO_TREES);
	}

	public static void addSequoiaTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SPARSE_SEQUOIA_TREES);
	}

	private static void addScorchedTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedTreePatchFeatures.SINGLE_SCORCHED_TREE);
	}

	private static void addAshLayer(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, LostWorldsPlacedFeatures.ASH_LAYER);
	}

	private static void addAlethopteris(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.ALETHOPTERIS_PATCH);
	}

	private static void addCycad(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.CYCAD_PATCH);
	}

	private static void addDuisbergia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.DUISBERGIA_PATCH);
	}

	private static void addDicksonia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.DICKSONIA_PATCH);
	}

	private static void addOsmunda(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.OSMUNDA_PATCH);
	}

	private static void addWilliamsonia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.WILLIAMSONIA_PATCH);
	}

	private static void addZamites(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedPlantPatchFeatures.ZAMITES_PATCH);
	}

	private static void addBasaltDiamondOre(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.BASALT_DIAMOND_ORE);
	}

	private static void addGeyser(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, LostWorldsPlacedFeatures.GEYSER_BLOCK);
	}
}
