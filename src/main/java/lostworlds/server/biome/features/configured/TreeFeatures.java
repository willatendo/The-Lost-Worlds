package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import java.util.List;
import java.util.OptionalInt;

import lostworlds.server.biome.features.placed.PlacedTreeFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class TreeFeatures {
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ARCTIC_CONIFER_TREES = register("arctic_conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARCTIC_CONIFER_TREE, 0.33333334F)), PlacedTreeFeatures.ARCTIC_CONIFER_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FROZEN_TREE = register("frozen_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(Blocks.SNOW_BLOCK), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> FROZEN_TREES = register("frozen_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.FROZEN_TREE, 0.33333334F)), PlacedTreeFeatures.FROZEN_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> BROKEN_TREE = register("broken_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BROKEN_TREES = register("broken_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BROKEN_TREE, 0.33333334F)), PlacedTreeFeatures.BROKEN_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CALAMITES_TREE = register("calamites_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(15, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CALAMITES_TREES = register("calamites_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CALAMITES_TREE, 0.33333334F)), PlacedTreeFeatures.CALAMITES_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONIFER_TREE = register("conifer_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CONIFER_TREE, 0.33333334F)), PlacedTreeFeatures.CONIFER_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CYPRESS_TREE = register("cypress_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get().get()), new ForkingTrunkPlacer(6, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get().get()), new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CYPRESS_TREES = register("cypress_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.CYPRESS_TREE, 0.33333334F)), PlacedTreeFeatures.CYPRESS_TREE));

	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> GINKGO_TREE = register("ginkgo_tree", LostWorldsFeatures.GINKGO_TREE);
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.GINKGO_TREE, 0.33333334F)), PlacedTreeFeatures.GINKGO_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(10, 2, 4), BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(1, 1, 2))).build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BABY_SEQUOIA_TREES = register("baby_sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.BABY_SEQUOIA_TREE, 0.33333334F)), PlacedTreeFeatures.BABY_SEQUOIA_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SEQUOIA_TREE = register("sequoia_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().get()), new GiantTrunkPlacer(32, 2, 14), BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().get()), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 4)), new TwoLayersFeatureSize(1, 1, 2))).build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SEQUOIA_TREES = register("sequoia_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SEQUOIA_TREE, 0.33333334F)), PlacedTreeFeatures.SEQUOIA_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ARAUCARIA_TREE = register("araucaria_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.ARAUCARIA_TREE, 0.33333334F)), PlacedTreeFeatures.ARAUCARIA_TREE));

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SCORCHED_TREE = register("scorched_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build());
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SCORCHED_TREES = register("scorched_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedTreeFeatures.SCORCHED_TREE, 0.33333334F)), PlacedTreeFeatures.SCORCHED_TREE));
}
