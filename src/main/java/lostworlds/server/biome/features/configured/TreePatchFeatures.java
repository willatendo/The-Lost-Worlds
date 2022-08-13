package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import java.util.List;

import lostworlds.server.biome.features.placed.PlacedTreeFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraftforge.registries.RegistryObject;

public class TreePatchFeatures {
	public static final RegistryObject<ConfiguredFeature<?, ?>> ARCTIC_CONIFER_TREES = register("arctic_conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARCTIC_CONIFER_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.ARCTIC_CONIFER_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FROZEN_TREES = register("frozen_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.FROZEN_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.FROZEN_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> BROKEN_TREES = register("broken_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BROKEN_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.BROKEN_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CALAMITES_TREES = register("calamites_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CALAMITES_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.CALAMITES_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CONIFER_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.CONIFER_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CYPRESS_TREES = register("cypress_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CYPRESS_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.CYPRESS_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.GINKGO_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.GINKGO_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> BABY_SEQUOIA_TREES = register("baby_sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BABY_SEQUOIA_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.BABY_SEQUOIA_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> SEQUOIA_TREES = register("sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SEQUOIA_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.SEQUOIA_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARAUCARIA_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.ARAUCARIA_TREE.getHolder().get()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> SCORCHED_TREES = register("scorched_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SCORCHED_TREE.getHolder().get(), 0.33333334F)), PlacedTreeFeatures.SCORCHED_TREE.getHolder().get()));

	public static void init() {
	}
}
