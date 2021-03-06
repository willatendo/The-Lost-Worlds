package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ThreeLayerFeature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TreeFeatures {
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", LostWorldsFeatures.FROZEN_TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_ARCTIC_CONIFER_TREES = register("scant_arctic_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ARCTIC_CONIFER_TREE.weighted(0.33333334F)), ARCTIC_CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FROZEN_TREE = register("frozen_tree", LostWorldsFeatures.FROZEN_TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> Blocks.SNOW_BLOCK.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_FROZEN_TREES = register("scant_frozen_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_FROZEN_TREES = register("sparse_frozen_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BROKEN_TREE = register("broken_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BROKEN_TREES = register("sparse_broken_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BROKEN_TREE.weighted(0.33333334F)), BROKEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CALAMITIES_TREE = register("calamties_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(15, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CALAMITIES_TREES = register("calamties_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CALAMITIES_TREE.weighted(0.33333334F)), CALAMITIES_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SCANT_CONIFER_TREES = register("scant_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES = register("sparse_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CYPRESS_TREE = register("cypress_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(0)), new ForkyTrunkPlacer(6, 2, 1), new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty()))).maxWaterDepth(3).decorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE, LeaveVineTreeDecorator.INSTANCE)).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_CYPRESS_TREES = register("scant_cypress_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CYPRESS_TREES = register("sparse_cypress_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<NoFeatureConfig, ?> GINKGO_TREE = register("ginkgo_tree", LostWorldsFeatures.GINKGO_TREE.configured(IFeatureConfig.NONE));
	public static final ConfiguredFeature<?, ?> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static final ConfiguredFeature<?, ?> GINKGO_FOREST_TREES = register("ginkgo_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.OAK.weighted(0.33333334F), Features.FANCY_OAK.weighted(0.1F)), Features.OAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(10, 2, 4), new TwoLayerFeature(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BABY_SEQUOIA_TREES = register("sparse_baby_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BABY_SEQUOIA_TREE.weighted(0.33333334F)), BABY_SEQUOIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SEQUOIA_TREE = register("sequoia_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)), new GiantTrunkPlacer(32, 2, 14), new TwoLayerFeature(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_SEQUOIA_TREES = register("sparse_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(SEQUOIA_TREE.weighted(0.33333334F)), SEQUOIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ARAUCARIA_TREE = register("araucaria_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get().getDefaultState()), new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get().getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ARAUCARIA_TREE.weighted(0.33333334F)), ARAUCARIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SCORCHED_TREE = register("scorched_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get().getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new StraightTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(SCORCHED_TREE.weighted(0.33333334F)), SCORCHED_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));

	public static void init() {
	}
}
