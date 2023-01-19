package lostworlds.server.world;

import java.util.List;

import com.google.common.collect.Lists;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.features.placed.PlacedDisksFeatures;
import lostworlds.server.biome.features.placed.PlacedOreFeatures;
import lostworlds.server.biome.features.placed.PlacedTreePatchFeatures;
import lostworlds.server.feature.BiomeLoadingEventHelper;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureGen {
	public static void init(BiomeLoadingEvent event) {
		if (LostWorldsUtils.SERVER_CONFIG.amberOre.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_AMBER_ORE.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.plantFossilsInOverworld.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.siltPatchGeneration.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.SILT_PATCH.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.mudDisksInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PlacedDisksFeatures.MUD_DISK.getHolder().get());
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.cypressTreesInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PlacedTreePatchFeatures.SCANT_CYPRESS_TREES.getHolder().get());
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedAraucariaTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_ARAUCARIA.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCalamitesTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CALAMITES.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedConiferTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CONIFER.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCypressTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CYPRESS.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedGinkgoTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_GINKGO.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedSequoiaTreeShouldSpawn.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_SEQUOIA.getHolder().get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_TINY_NEST.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_SMALL_NEST.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_MEDIUM_NEST.getHolder().get());
			BiomeLoadingEventHelper.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_LARGE_NEST.getHolder().get());
		}
	}
}
