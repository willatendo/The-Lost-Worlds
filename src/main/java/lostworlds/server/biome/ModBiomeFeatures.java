package lostworlds.server.biome;

import lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures;
import lostworlds.server.biome.features.configured.OreFeatures;
import lostworlds.server.biome.features.configured.PlantPatchFeatures;
import lostworlds.server.biome.features.configured.TreeFeatures;
import lostworlds.server.biome.features.configured.WaterFeatures;
import lostworlds.server.dimension.carver.LostWorldsConfiguredCarvers;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;

public class ModBiomeFeatures {
	// Permian
	public static void permianAshyMedows(BiomeGenerationSettings.Builder builder) {
		addScorchedTrees(builder);
		addAshLayer(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
	}

	public static void permianConiferForest(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
	}

	public static void permianDesert(BiomeGenerationSettings.Builder builder) {
		addPermianDesertPlants(builder);
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
	}

	public static void permianDriedPlains(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void permianGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addGinkgoTrees(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addSpring(builder);
		addAlethopteris(builder);
		addCycad(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void permianFloodBasalts(BiomeGenerationSettings.Builder builder) {
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA);
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, Features.BASALT_PILLAR);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OreFeatures.PERMIAN_MAGMA_ORE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DELTA);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA_DOUBLE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.LARGE_BASALT_COLUMNS);
	}

	public static void permianMountains(BiomeGenerationSettings.Builder builder) {
		addConiferTrees(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addPermianEmerald(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);
	}

	public static void permianOcean(BiomeGenerationSettings.Builder builder) {
		addPermianOres(builder);
		addModUnderwaterCarvers(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
	}

	public static void permianMarsh(BiomeGenerationSettings.Builder builder) {
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.CALAMITES_SUCKOWII);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.CALAMITIES_TREES);
	}

	public static void permianRiver(BiomeGenerationSettings.Builder builder) {
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
	}

	public static void permianShore(BiomeGenerationSettings.Builder builder) {
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
	}

	public static void permianCoralReef(BiomeGenerationSettings.Builder builder) {
		addPermianOres(builder);
		addModUnderwaterCarvers(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.MOD_CORAL_REEF);
	}

	public static void permianPlains(BiomeGenerationSettings.Builder builder) {
		addFernGroundClutter(builder);
		addPermianRock(builder);
		addPermianOres(builder);
		addModCarvers(builder);
		addPermianLakes(builder);
		addPermianUndergroundVariety(builder);
		addAlethopteris(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	// Jurassic
	public static void jurassicConiferForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addConiferTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
		addJurassicLakes(builder);
	}

	public static void jurassicGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addGinkgoTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicAraucariaForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addAraucariaTrees(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
		addJurassicLakes(builder);
	}

	public static void jurassicPlains(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addAlethopteris(builder);
		addOsmunda(builder);
		addCycad(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicMountains(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addJurassicRock(builder);
		addJurassicOres(builder);
		addJurassicEmerald(builder);
		addJurassicUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addDicksonia(builder);
		addOsmunda(builder);
		addJurassicLakes(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void jurassicVolcanicRange(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addJurassicOres(builder);
		addJurassicEmerald(builder);
		addExtraJurassicDiamonds(builder);
		addJurassicUndergroundVariety(builder);
		addGeyser(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_MAGMA_ORE);
	}

	public static void jurassicDesert(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);
	}

	public static void jurassicRedwoods(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addSpring(builder);
		addJurassicUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_SEQUOIA_TREES);
	}

	public static void jurassicMarsh(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void jurassicSwamp(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CYPRESS_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CYPRESS_TREES);
	}

	public static void jurassicBog(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
	}

	public static void jurassicFen(BiomeGenerationSettings.Builder builder) {
		addJurassicRock(builder);
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addJurassicLakes(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
	}

	public static void jurassicOcean(BiomeGenerationSettings.Builder builder) {
		addJurassicOres(builder);
		addModUnderwaterCarvers(builder);
		addPermianUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
	}

	public static void jurassicCoralReef(BiomeGenerationSettings.Builder builder) {
		addJurassicOres(builder);
		addModUnderwaterCarvers(builder);
		addJurassicUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.MOD_CORAL_REEF);
	}

	public static void jurassicRiver(BiomeGenerationSettings.Builder builder) {
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
	}

	public static void jurassicShore(BiomeGenerationSettings.Builder builder) {
		addJurassicOres(builder);
		addModCarvers(builder);
		addJurassicUndergroundVariety(builder);
	}

	// Cretaceous
	public static void cretaceousConiferForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addConiferTrees(builder);
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);
	}

	public static void cretaceousGinkgoForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addGinkgoTrees(builder);
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addDuisbergia(builder);
		addAlethopteris(builder);
		addCycad(builder);
		addDicksonia(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousAraucariaForest(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addAraucariaTrees(builder);
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addZamites(builder);
		addWilliamsonia(builder);
		addAlethopteris(builder);
		addDicksonia(builder);
	}

	public static void cretaceousRedwoods(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_SEQUOIA_TREES);
	}

	public static void cretaceousGameTrail(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_BROKEN_TREES);
	}

	public static void cretaceousMedow(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.ARCHAEFRUTUS_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.EUDICOTS_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.MAGNOLIA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousPlains(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addDicksonia(builder);
		addOsmunda(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousMountains(BiomeGenerationSettings.Builder builder) {
		addModCarvers(builder);
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addCretaceousEmerald(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addAlethopteris(builder);
		addOsmunda(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
	}

	public static void cretaceousArctic(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_FROZEN_TREES);
	}

	public static void cretaceousArcticSpires(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, Features.ICE_SPIKE);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_FROZEN_TREES);
	}

	public static void cretaceousFrozenForest(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_ARCTIC_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_FROZEN_TREES);
	}

	public static void cretaceousDesert(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addSpring(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);
		addModCarvers(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH_BADLANDS);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_BROKEN_TREES);
	}

	public static void cretaceousFloodBasalts(BiomeGenerationSettings.Builder builder) {
		addBasaltDiamondOre(builder);
		addGeyser(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA);
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, Features.BASALT_PILLAR);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OreFeatures.CRETACEOUS_MAGMA_ORE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DELTA);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA_DOUBLE);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.SMALL_BASALT_COLUMNS);
		builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.LARGE_BASALT_COLUMNS);
	}

	public static void cretaceousMarsh(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);
		addZamites(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CONIFER_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void cretaceousSwamp(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CYPRESS_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_CYPRESS_TREES);
	}

	public static void cretaceousBog(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
		addFernGroundClutter(builder);

		BiomeDefaultFeatures.addLightBambooVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
	}

	public static void cretaceousFen(BiomeGenerationSettings.Builder builder) {
		addCretaceousRock(builder);
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
		addAlethopteris(builder);
		addFernGroundClutter(builder);

		BiomeDefaultFeatures.addLightBambooVegetation(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.BRAZILEA_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SCANT_CONIFER_TREES);
	}

	public static void coldCretaceousOcean(BiomeGenerationSettings.Builder builder) {
		addCretaceousOres(builder);
		addModUnderwaterCarvers(builder);
		addCretaceousUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
	}

	public static void cretaceousOcean(BiomeGenerationSettings.Builder builder) {
		addCretaceousOres(builder);
		addModUnderwaterCarvers(builder);
		addCretaceousUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
	}

	public static void cretaceousCoralReef(BiomeGenerationSettings.Builder builder) {
		addCretaceousOres(builder);
		addModUnderwaterCarvers(builder);
		addCretaceousUndergroundVariety(builder);

		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.SPONGE_COLONEY);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LostWorldsConfiguredFeatures.MOD_CORAL_REEF);
	}

	public static void cretaceousShore(BiomeGenerationSettings.Builder builder) {
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
	}

	public static void cretaceousRiver(BiomeGenerationSettings.Builder builder) {
		addCretaceousOres(builder);
		addModCarvers(builder);
		addCretaceousUndergroundVariety(builder);
	}

	// Overworld
	public static void araucariaForest(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
		addAraucariaTrees(builder);
	}

	public static void coniferForest(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
		addConiferTrees(builder);
	}

	public static void ginkgoForest(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.GINKGO_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.GINKGO_FOREST_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.BIRCH_OTHER);
		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
	}

	public static void redwoodsForest(BiomeGenerationSettings.Builder builder) {
		builder.surfaceBuilder(SurfaceBuilders.GRASS);

		BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addForestFlowers(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);

		addConiferTrees(builder);
		addSequoiaTrees(builder);
	}

	public static void volcano(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		addAshLayer(builder);
		addScorchedTrees(builder);
	}

	// Permian Features
	private static void addPermianUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_DIRT_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_GRAVEL_ORE);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_LIMESTONE_ORE);
	}

	private static void addPermianDesertPlants(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.PERMIAN_DESERT_SHRUB_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.PERMIAN_DESERT_FERNS_PATCH);
	}

	private static void addFernGroundClutter(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.FERN_PATCH);
	}

	private static void addPermianRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsConfiguredFeatures.PERMIAN_ROCK);
	}

	private static void addPermianOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_COAL_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_DIAMOND_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_GOLD_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_IRON_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_LAPIS_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_REDSTONE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_COPPER_ORE);
	}

	private static void addPermianEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.PERMIAN_EMERALD_ORE);
	}

	private static void addPermianLakes(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LAKES, WaterFeatures.PERMIAN_WATER_LAKE);
		builder.addFeature(GenerationStep.Decoration.LAKES, WaterFeatures.PERMIAN_LAVA_LAKE);
	}

	// Jurassic Features
	private static void addJurassicUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_DIRT_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_GRAVEL_ORE);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_LIMESTONE_ORE);
	}

	private static void addJurassicRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsConfiguredFeatures.JURASSIC_ROCK);
	}

	private static void addJurassicOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_COAL_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_DIAMOND_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_GOLD_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_IRON_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_LAPIS_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_REDSTONE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_COPPER_ORE);
	}

	private static void addJurassicEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_EMERALD_ORE);
	}

	private static void addExtraJurassicDiamonds(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_DIAMOND_ORE);
	}

	private static void addJurassicLakes(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LAKES, WaterFeatures.JURASSIC_WATER_LAKE);
		builder.addFeature(GenerationStep.Decoration.LAKES, WaterFeatures.JURASSIC_LAVA_LAKE);
	}

	// Cretaceous Features
	private static void addCretaceousUndergroundVariety(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_DIRT);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_GRAVEL);

		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_LATERLITE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_RAW_MARBLE_ORE);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.JURASSIC_LIMESTONE_ORE);
	}

	private static void addCretaceousRock(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, LostWorldsConfiguredFeatures.CRETACEOUS_ROCK);
	}

	private static void addCretaceousOres(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_COAL);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_DIAMOND);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_IRON);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_LAPIS);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_REDSTONE);
//		builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, LostWorldsConfiguredFeatures.COPPER_ORE);
	}

	private static void addCretaceousEmerald(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.ORE_EMERALD);
	}

	// General Features
	private static void addModCarvers(BiomeGenerationSettings.Builder builder) {
		builder.addCarver(GenerationStep.Carving.AIR, LostWorldsConfiguredCarvers.CAVE_CARVER);
		builder.addCarver(GenerationStep.Carving.AIR, LostWorldsConfiguredCarvers.CANYON_CARVER);
	}

	private static void addModUnderwaterCarvers(BiomeGenerationSettings.Builder builder) {
		builder.addCarver(GenerationStep.Carving.LIQUID, LostWorldsConfiguredCarvers.UNDERWATER_CAVE_CARVER);
		builder.addCarver(GenerationStep.Carving.LIQUID, LostWorldsConfiguredCarvers.UNDERWATER_CANYON_CARVER);
	}

	private static void addSpring(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WaterFeatures.ANCIENT_SPRING);
	}

	public static void addAraucariaTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.ARAUCARIA_TREES);
	}

	public static void addConiferTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.CONIFER_TREES);
	}

	private static void addGinkgoTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.GINKGO_TREES);
	}

	public static void addSequoiaTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_BABY_SEQUOIA_TREES);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SPARSE_SEQUOIA_TREES);
	}

	private static void addScorchedTrees(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TreeFeatures.SINGLE_SCORCHED_TREE);
	}

	private static void addAshLayer(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, LostWorldsConfiguredFeatures.ASH_LAYER);
	}

	private static void addAlethopteris(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.ALETHOPTERIS_PATCH);
	}

	private static void addCycad(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.CYCAD_PATCH);
	}

	private static void addDuisbergia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.DUISBERGIA_PATCH);
	}

	private static void addDicksonia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.DICKSONIA_PATCH);
	}

	private static void addOsmunda(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.OSMUNDA_PATCH);
	}

	private static void addWilliamsonia(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.WILLIAMSONIA_PATCH);
	}

	private static void addZamites(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlantPatchFeatures.ZAMITES_PATCH);
	}

	private static void addBasaltDiamondOre(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.BASALT_DIAMOND_ORE);
	}

	private static void addGeyser(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, LostWorldsConfiguredFeatures.GEYSER_BLOCK);
	}
}
