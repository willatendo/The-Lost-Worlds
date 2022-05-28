package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import java.util.List;

import lostworlds.server.biome.features.placed.PlacedTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;

public class TreePatchFeatures {
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ARCTIC_CONIFER_TREES = register("arctic_conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARCTIC_CONIFER_TREE, 0.33333334F)), PlacedTreeFeatures.ARCTIC_CONIFER_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> FROZEN_TREES = register("frozen_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.FROZEN_TREE, 0.33333334F)), PlacedTreeFeatures.FROZEN_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BROKEN_TREES = register("broken_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BROKEN_TREE, 0.33333334F)), PlacedTreeFeatures.BROKEN_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CALAMITES_TREES = register("calamites_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CALAMITES_TREE, 0.33333334F)), PlacedTreeFeatures.CALAMITES_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CONIFER_TREE, 0.33333334F)), PlacedTreeFeatures.CONIFER_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CYPRESS_TREES = register("cypress_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CYPRESS_TREE, 0.33333334F)), PlacedTreeFeatures.CYPRESS_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.GINKGO_TREE, 0.33333334F)), PlacedTreeFeatures.GINKGO_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BABY_SEQUOIA_TREES = register("baby_sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BABY_SEQUOIA_TREE, 0.33333334F)), PlacedTreeFeatures.BABY_SEQUOIA_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SEQUOIA_TREES = register("sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SEQUOIA_TREE, 0.33333334F)), PlacedTreeFeatures.SEQUOIA_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARAUCARIA_TREE, 0.33333334F)), PlacedTreeFeatures.ARAUCARIA_TREE));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SCORCHED_TREES = register("scorched_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SCORCHED_TREE, 0.33333334F)), PlacedTreeFeatures.SCORCHED_TREE));

	public static void init() {
	}
}
