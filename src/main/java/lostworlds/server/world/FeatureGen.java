package lostworlds.server.world;

import java.util.List;

import com.google.common.collect.Lists;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.features.placed.PlacedDisksFeatures;
import lostworlds.server.biome.features.placed.PlacedOreFeatures;
import lostworlds.server.biome.features.placed.PlacedTreePatchFeatures;
import lostworlds.server.feature.FeatureAdder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureGen {
	public static void init(BiomeLoadingEvent event) {
		if (LostWorldsUtils.SERVER_CONFIG.amberOre.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_AMBER_ORE);
		}

		if (LostWorldsUtils.SERVER_CONFIG.plantFossilsInOverworld.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES);
		}

		if (LostWorldsUtils.SERVER_CONFIG.siltPatchGeneration.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.SILT_PATCH);
		}

		if (LostWorldsUtils.SERVER_CONFIG.mudDisksInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				FeatureAdder.addFeature(event, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PlacedDisksFeatures.MUD_DISK);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.cypressTreesInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				FeatureAdder.addFeature(event, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PlacedTreePatchFeatures.SCANT_CYPRESS_TREES);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedAraucariaTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_ARAUCARIA);
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCalamitesTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CALAMITES);
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedConiferTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CONIFER);
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCypressTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_CYPRESS);
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedGinkgoTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_GINKGO);
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedSequoiaTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.PETRIFIED_SEQUOIA);
		}

		if (LostWorldsUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_TINY_NEST);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_SMALL_NEST);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_MEDIUM_NEST);
			FeatureAdder.addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, PlacedOreFeatures.OVERWORLD_LARGE_NEST);
		}
	}
}
