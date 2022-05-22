package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.util.UniformInt;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class TreeFeatures {
	public static final ConfiguredFeature<TreeConfiguration, ?> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", LostWorldsFeatures.FROZEN_TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_ARCTIC_CONIFER_TREES = register("scant_arctic_conifer_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(ARCTIC_CONIFER_TREE.weighted(0.33333334F)), ARCTIC_CONIFER_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(5, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> FROZEN_TREE = register("frozen_tree", LostWorldsFeatures.FROZEN_TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> Blocks.SNOW_BLOCK.defaultBlockState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_FROZEN_TREES = register("scant_frozen_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_FROZEN_TREES = register("sparse_frozen_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> BROKEN_TREE = register("broken_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleStateProvider(Blocks.AIR.defaultBlockState()), new MegaJungleFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(0), 2), new FancyTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BROKEN_TREES = register("sparse_broken_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(BROKEN_TREE.weighted(0.33333334F)), BROKEN_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> CALAMITIES_TREE = register("calamties_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(15, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CALAMITIES_TREES = register("calamties_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CALAMITIES_TREE.weighted(0.33333334F)), CALAMITIES_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SCANT_CONIFER_TREES = register("scant_conifer_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES = register("sparse_conifer_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> CYPRESS_TREE = register("cypress_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new DarkOakFoliagePlacer(UniformInt.fixed(1), UniformInt.fixed(0)), new ForkingTrunkPlacer(6, 2, 1), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).maxWaterDepth(3).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_CYPRESS_TREES = register("scant_cypress_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CYPRESS_TREES = register("sparse_cypress_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));

	public static final ConfiguredFeature<NoneFeatureConfiguration, ?> GINKGO_TREE = register("ginkgo_tree", LostWorldsFeatures.GINKGO_TREE.configured(FeatureConfiguration.NONE));
	public static final ConfiguredFeature<?, ?> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(3, 0.1F, 1))));

	public static final ConfiguredFeature<?, ?> GINKGO_FOREST_TREES = register("ginkgo_forest_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(Features.OAK.weighted(0.33333334F), Features.FANCY_OAK.weighted(0.1F)), Features.OAK)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(8, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(10, 2, 4), new TwoLayersFeatureSize(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BABY_SEQUOIA_TREES = register("sparse_baby_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(BABY_SEQUOIA_TREE.weighted(0.33333334F)), BABY_SEQUOIA_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> SEQUOIA_TREE = register("sequoia_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new MegaPineFoliagePlacer(UniformInt.fixed(0), UniformInt.fixed(0), UniformInt.of(13, 4)), new GiantTrunkPlacer(32, 2, 14), new TwoLayersFeatureSize(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_SEQUOIA_TREES = register("sparse_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(SEQUOIA_TREE.weighted(0.33333334F)), SEQUOIA_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> ARAUCARIA_TREE = register("araucaria_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(ARAUCARIA_TREE.weighted(0.33333334F)), ARAUCARIA_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(3, 0.1F, 1))));

	public static final ConfiguredFeature<TreeConfiguration, ?> SCORCHED_TREE = register("scorched_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get().getDefaultState()), new SimpleStateProvider(Blocks.AIR.defaultBlockState()), new MegaJungleFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(0), 2), new StraightTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(SCORCHED_TREE.weighted(0.33333334F)), SCORCHED_TREE)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1))));

	public static void init() {
	}
}
