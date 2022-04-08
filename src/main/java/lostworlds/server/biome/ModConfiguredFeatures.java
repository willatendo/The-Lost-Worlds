package lostworlds.server.biome;

import static lostworlds.server.util.BlockGetter.getStateWhenCan;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.Damage;
import lostworlds.server.block.Egg;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.PlantFossilBlock;
import lostworlds.server.block.Plants;
import lostworlds.server.block.SoftDirtBlock;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.placement.LostWorldsPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.SingleRandomFeature;
import net.minecraft.world.gen.feature.ThreeLayerFeature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {
//	public static final ConfiguredFeature<?, ?> COPPER_ORE = register("copper_ore", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, LostWorldsBlocks.COPPER_ORE.getDefaultState(), LostWorldsConfig.SERVER_CONFIG.copperVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.copperRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.copperCountPerChunk.get()));

	// Plants
	public static final ConfiguredFeature<?, ?> ARCHAEFRUTUS_PATCH = register("archaefrutus_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.ARCHAEFRUTUS.defaultBlockState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> ALETHOPTERIS_PATCH = register("alethopteris_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.ALETHOPTERIS.defaultBlockState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.BRAZILEA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	public static final ConfiguredFeature<?, ?> CALAMITES_SUCKOWII = register("calamites_suckwii", LostWorldsFeatures.CALAMITES_SUCKOWII.configured(new ProbabilityConfig(0.2F)).decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D))));
	public static final ConfiguredFeature<?, ?> CYCAD_PATCH = register("cycad_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CYCAD_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> DUISBERGIA_PATCH = register("duisbergia_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.DUISBERGIA.defaultBlockState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> DICKSONIA_PATCH = register("dicksonia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.DICKSONIA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> EUDICOTS_PATCH = register("eudicots_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.EUDICOTS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH.configured(ModConfiguredFeatures.ModBlockClusterFeatureConfig.FERN_CONFIG));
	public static final ConfiguredFeature<?, ?> MAGNOLIA_PATCH = register("magnolia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.MAGNOLIA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> OSMUNDA_PATCH = register("osmunda_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.OSMUNDA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> WILLIAMSONIA_PATCH = register("williamsonia_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.WILLIAMSONIA.defaultBlockState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> ZAMITES_PATCH = register("zamites_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ZAMITES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	// Trees
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", LostWorldsFeatures.FROZEN_TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.CONIFER_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_ARCTIC_CONIFER_TREES = register("scant_arctic_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ARCTIC_CONIFER_TREE.weighted(0.33333334F)), ARCTIC_CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FROZEN_TREE = register("frozen_tree", LostWorldsFeatures.FROZEN_TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.SNOW_BLOCK.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_FROZEN_TREES = register("scant_frozen_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_FROZEN_TREES = register("sparse_frozen_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FROZEN_TREE.weighted(0.33333334F)), FROZEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BROKEN_TREE = register("broken_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BROKEN_TREES = register("sparse_broken_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BROKEN_TREE.weighted(0.33333334F)), BROKEN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CALAMITIES_TREE = register("calamties_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CALAMITES_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.CALAMITES_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(15, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CALAMITIES_TREES = register("calamties_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CALAMITIES_TREE.weighted(0.33333334F)), CALAMITIES_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.CONIFER_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SCANT_CONIFER_TREES = register("scant_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES = register("sparse_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CYPRESS_TREE = register("cypress_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CYPRESS_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.CYPRESS_LEAVES.defaultBlockState()), new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(0)), new ForkyTrunkPlacer(6, 2, 1), new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty()))).maxWaterDepth(3).decorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE, LeaveVineTreeDecorator.INSTANCE)).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> SCANT_CYPRESS_TREES = register("scant_cypress_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CYPRESS_TREES = register("sparse_cypress_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CYPRESS_TREE.weighted(0.33333334F)), CYPRESS_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GINKGO_TREE = register("ginkgo_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.GINKGO_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.GINKGO_LEAVES.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static final ConfiguredFeature<?, ?> GINKGO_FOREST_TREES = register("ginkgo_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.OAK.weighted(0.33333334F), Features.FANCY_OAK.weighted(0.1F)), Features.OAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.SEQUOIA_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.SEQUOIA_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(10, 2, 4), new TwoLayerFeature(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_BABY_SEQUOIA_TREES = register("sparse_baby_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BABY_SEQUOIA_TREE.weighted(0.33333334F)), BABY_SEQUOIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SEQUOIA_TREE = register("sequoia_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.SEQUOIA_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.SEQUOIA_LEAVES.defaultBlockState()), new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)), new GiantTrunkPlacer(32, 2, 14), new TwoLayerFeature(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> SPARSE_SEQUOIA_TREES = register("sparse_sequoia_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(SEQUOIA_TREE.weighted(0.33333334F)), SEQUOIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ARAUCARIA_TREE = register("araucaria_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.ARAUCARIA_LOG.defaultBlockState()), new SimpleBlockStateProvider(LostWorldsBlocks.ARAUCARIA_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> ARAUCARIA_TREES = register("araucaria_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ARAUCARIA_TREE.weighted(0.33333334F)), ARAUCARIA_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SCORCHED_TREE = register("scorched_tree", LostWorldsFeatures.SCORCHED_TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.SCORCHED_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ModConfiguredFeatures.SCORCHED_TREE.weighted(0.33333334F)), ModConfiguredFeatures.SCORCHED_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));

	// Ores
	public static final ConfiguredFeature<?, ?> JURASSIC_MAGMA_ORE = register("jurassic_magma_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), Blocks.MAGMA_BLOCK.defaultBlockState(), 33)).decorated(Placement.MAGMA.configured(NoPlacementConfig.INSTANCE)).squared().count(4));
	public static final ConfiguredFeature<?, ?> CRETACEOUS_MAGMA_ORE = register("cretaceous_magma_ore", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.MAGMA_BLOCK.defaultBlockState(), 33)).decorated(Placement.MAGMA.configured(NoPlacementConfig.INSTANCE)).squared().count(4));

	public static final ConfiguredFeature<?, ?> PETRIFIED_ARAUCARIA = register("petrified_araucaria", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PETRIFIED_ARAUCARIA_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.petrifiedAraucariaChance.get())));
	public static final ConfiguredFeature<?, ?> PETRIFIED_CALAMITES = register("petrified_calamties", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PETRIFIED_CALAMITES_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.petrifiedCalamitesChance.get())));
	public static final ConfiguredFeature<?, ?> PETRIFIED_CONIFER = register("petrified_conifer", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PETRIFIED_CONIFER_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.petrifiedConiferChance.get())));
	public static final ConfiguredFeature<?, ?> PETRIFIED_GINKGO = register("petrified_ginkgo", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PETRIFIED_GINKGO_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.petrifiedGinkgoChance.get())));

	public static final ConfiguredFeature<?, ?> BASALT_DIAMOND_ORE = register("basalt_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.BASALT.get(), LostWorldsBlocks.BASALT_DIAMOND_ORE.defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(40, 0, 100))).squared().count(30));

	public static final ConfiguredFeature<?, ?> JURASSIC_COAL_ORE = register("jurassic_coal_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_COAL_ORE.defaultBlockState(), 17)).range(128).squared().count(20));
	public static final ConfiguredFeature<?, ?> JURASSIC_IRON_ORE = register("jurassic_iron_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_IRON_ORE.defaultBlockState(), 9)).range(64).squared().count(20));
	public static final ConfiguredFeature<?, ?> JURASSIC_GOLD_ORE = register("jurassic_gold_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_GOLD_ORE.defaultBlockState(), 9)).range(32).squared().count(2));
	public static final ConfiguredFeature<?, ?> JURASSIC_REDSTONE_ORE = register("jurassic_redstone_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_REDSTONE_ORE.defaultBlockState(), 8)).range(16).squared().count(8));
	public static final ConfiguredFeature<?, ?> JURASSIC_DIAMOND_ORE = register("jurassic_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_DIAMOND_ORE.defaultBlockState(), 8)).range(16).squared());
	public static final ConfiguredFeature<?, ?> JURASSIC_LAPIS_ORE = register("jurassic_lapis_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_LAPIS_ORE.defaultBlockState(), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared());
	public static final ConfiguredFeature<?, ?> JURASSIC_EMERALD_ORE = register("jurassic_emerald_ore", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(LostWorldsBlocks.JURASSIC_STONE.defaultBlockState(), LostWorldsBlocks.JURASSIC_EMERALD_ORE.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> JURASSIC_COPPER_ORE = register("jurassic_copper_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.JURASSIC_COPPER_ORE.defaultBlockState(), LostWorldsConfig.SERVER_CONFIG.copperVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.copperRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.copperCountPerChunk.get()));

	public static final ConfiguredFeature<?, ?> JURASSIC_DIRT_ORE = register("jurassic_dirt_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), Blocks.DIRT.defaultBlockState(), 33)).range(256).squared().count(10));
	public static final ConfiguredFeature<?, ?> JURASSIC_GRAVEL_ORE = register("jurassic_gravel_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), Blocks.GRAVEL.defaultBlockState(), 33)).range(256).squared().count(8));

	public static final ConfiguredFeature<?, ?> JURASSIC_LATERLITE_ORE = register("jurassic_laterlite_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.LATERLITE.defaultBlockState(), 33)).range(256).squared().count(10));
	public static final ConfiguredFeature<?, ?> JURASSIC_RAW_MARBLE_ORE = register("jurassic_raw_marble_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.RAW_MARBLE.defaultBlockState(), 33)).range(256).squared().count(8));
	public static final ConfiguredFeature<?, ?> JURASSIC_LIMESTONE_ORE = register("jurassic_limestone_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.JURASSIC_STONE.get(), LostWorldsBlocks.LIMESTONE.defaultBlockState(), 33)).range(256).squared().count(8));

	// Rocks
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.ROCK.configured(new BlockStateFeatureConfig(getStateWhenCan(LostWorldsBlocks.PERMIAN_COBBLESTONE))).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.ROCK.configured(new BlockStateFeatureConfig(LostWorldsBlocks.JURASSIC_COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.ROCK.configured(new BlockStateFeatureConfig(Blocks.COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));

	// Lakes
	public static final ConfiguredFeature<?, ?> PERMIAN_WATER_LAKE = register("permian_water_lake", LostWorldsFeatures.PERMIAN_LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final ConfiguredFeature<?, ?> PERMIAN_LAVA_LAKE = register("permian_lava_lake", LostWorldsFeatures.PERMIAN_LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));

	// Fossils
	public static final ConfiguredFeature<?, ?> OVERWORLD_AMBER_ORE = register("overworld_amber_ore", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, LostWorldsBlocks.AMBER_ORE.defaultBlockState(), LostWorldsConfig.SERVER_CONFIG.amberVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.amberRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.amberCountPerChunk.get()));

	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS = register("overworld_plant_fossil_alethopteris", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_BRAZILEA = register("overworld_plant_fossil_brazilea", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.BRAZILEA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII = register("overworld_plant_fossil_calamites_suckowii", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CALAMITES_SUCKOWII).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS = register("overworld_plant_fossil_cephalotaxus", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CEPHALOTAXUS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_DILLHOFFIA = register("overworld_plant_fossil_dillhoffia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DILLHOFFIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_DUISBERGIA = register("overworld_plant_fossil_duisbergia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DUISBERGIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_OSMUNDA = register("overworld_plant_fossil_osmunda", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.OSMUNDA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA = register("overworld_plant_fossil_williamsonia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.WILLIAMSONIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_ZAMITES = register("overworld_plant_fossil_zamites", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), LostWorldsBlocks.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ZAMITES).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE).chance(LostWorldsConfig.SERVER_CONFIG.plantFossilChance.get())));

	public static final ConfiguredFeature<?, ?> OVERWORLD_TINY_NEST = register("overworld_tiny_nest", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SOFT_DIRT.defaultBlockState().setValue(SoftDirtBlock.EGG, Egg.TINY))).decorated(LostWorldsPlacements.NEST.configured(IPlacementConfig.NONE)).chance(LostWorldsConfig.SERVER_CONFIG.fossilChance.get()));
	public static final ConfiguredFeature<?, ?> OVERWORLD_SMALL_NEST = register("overworld_small_nest", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SOFT_DIRT.defaultBlockState().setValue(SoftDirtBlock.EGG, Egg.SMALL))).decorated(LostWorldsPlacements.NEST.configured(IPlacementConfig.NONE)).chance(LostWorldsConfig.SERVER_CONFIG.fossilChance.get()));
	public static final ConfiguredFeature<?, ?> OVERWORLD_MEDIUM_NEST = register("overworld_medium_nest", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SOFT_DIRT.defaultBlockState().setValue(SoftDirtBlock.EGG, Egg.MEDIUM))).decorated(LostWorldsPlacements.NEST.configured(IPlacementConfig.NONE)).chance(LostWorldsConfig.SERVER_CONFIG.fossilChance.get()));
	public static final ConfiguredFeature<?, ?> OVERWORLD_LARGE_NEST = register("overworld_large_nest", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SOFT_DIRT.defaultBlockState().setValue(SoftDirtBlock.EGG, Egg.LARGE))).decorated(LostWorldsPlacements.NEST.configured(IPlacementConfig.NONE)).chance(LostWorldsConfig.SERVER_CONFIG.fossilChance.get()));

	// Misc Decoration
	public static final ConfiguredFeature<?, ?> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER_PLACEMENT.configured(IFeatureConfig.NONE));
	public static final ConfiguredFeature<?, ?> PERMIAN_CORAL_REEF = register("permian_coral_reef", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> {
		return Feature.CORAL_TREE.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_CLAW.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_MUSHROOM.configured(IFeatureConfig.NONE);
	}))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(20, 400.0D, 0.0D))));
	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER_BLOCK_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, LostWorldsUtils.rL(id), configuredFeature);
	}

	static class ModBlockClusterFeatureConfig {
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.PERMIAN_DESERT_SHRUB.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(9).build();
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_FERNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.PERMIAN_DESERT_FERNS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(4).build();

		public static final BlockClusterFeatureConfig GEYSER_BLOCK = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.GEYSER_BLOCK.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();

		public static final BlockClusterFeatureConfig FERN_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

		public static final BlockClusterFeatureConfig CYCAD_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.CYCAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig DICKSONIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.DICKSONIA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig EUDICOTS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.EUDICOTS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig MAGNOLIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.MAGNOLIA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig OSMUNDA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.OSMUNDA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig ZAMITES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LostWorldsBlocks.ZAMITES.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	}
}
