package lostworlds.library.biome;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.library.foliageplacer.ConiferFoliagePlacer;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
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
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.SingleRandomFeature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures 
{
	//Permian
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", FeatureInit.PERMIAN_ROCK.configured(new BlockStateFeatureConfig(BlockInit.PERMIAN_COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));	
	
	public static final ConfiguredFeature<?, ?> PERMIAN_CORAL_REEF = register("permian_coral_reef", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> 
	{
		return Feature.CORAL_TREE.configured(IFeatureConfig.NONE);
	}, () -> 
	{
		return Feature.CORAL_CLAW.configured(IFeatureConfig.NONE);
	}, () -> 
	{
		return Feature.CORAL_MUSHROOM.configured(IFeatureConfig.NONE);
	}))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(20, 400.0D, 0.0D))));
	
	//General
	public static final ConfiguredFeature<?, ?> ASHY_SHRUB = register("ashy_shrub", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ASHY_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> ASH_LAYER = FeatureInit.ASH_LAYER_PLACEMENT.configured(IFeatureConfig.NONE);
	public static final ConfiguredFeature<?, ?> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BRAZILEA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	public static final ConfiguredFeature<?, ?> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH.configured(ModConfiguredFeatures.ModBlockClusterFeatureConfig.FERN_CONFIG));	

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CALAMITIES_TREE = register("calamties_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CALAMITES_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.CALAMITES_LEAVES.defaultBlockState()), new ConiferFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(15, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CALAMITIES_TREES = register("calamties_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CALAMITIES_TREE.weighted(0.33333334F)), CALAMITIES_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.CONIFER_LEAVES.defaultBlockState()), new ConiferFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SCANT_CONIFER_TREES = register("scant_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES = register("sparse_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> CONIFER_STICKS = register("conifer_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CONIFER_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GINKGO_TREE = register("ginkgo_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GINKGO_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.GINKGO_LEAVES.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> GINKGO_STICKS = register("ginkgo_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CONIFER_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SCORCHED_TREE = register("scorched_tree", FeatureInit.SCORCHED_TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SCORCHED_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ModConfiguredFeatures.SCORCHED_TREE.weighted(0.33333334F)), ModConfiguredFeatures.SCORCHED_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
	public static final ConfiguredFeature<?, ?> SCORCHED_STICKS = register("ginkgo_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.SCORCHED_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", FeatureInit.GEYSER_BLOCK_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", FeatureInit.SPONGE_COLONEY_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

	public static final ConfiguredFeature<?, ?> BASALT_DIAMOND_ORE = register("basalt_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModFillerBlockTypes.BASALT, BlockInit.BASALT_DIAMOND_ORE.defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(40, 0, 100))).squared().count(30));
	
	public static final ConfiguredFeature<?, ?> PERMIAN_COAL_ORE = register("ore_coal", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_COAL_ORE.defaultBlockState(), 17)).range(128).squared().count(20));
	public static final ConfiguredFeature<?, ?> PERMIAN_IRON_ORE = register("ore_iron", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_IRON_ORE.defaultBlockState(), 9)).range(64).squared().count(20));
	public static final ConfiguredFeature<?, ?> PERMIAN_GOLD_ORE = register("ore_gold", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_GOLD_ORE.defaultBlockState(), 9)).range(32).squared().count(2));
	public static final ConfiguredFeature<?, ?> PERMIAN_REDSTONE_ORE = register("ore_redstone", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_REDSTONE_ORE.defaultBlockState(), 8)).range(16).squared().count(8));
	public static final ConfiguredFeature<?, ?> PERMIAN_DIAMOND_ORE = register("ore_diamond", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_DIAMOND_ORE.defaultBlockState(), 8)).range(16).squared());
	public static final ConfiguredFeature<?, ?> PERMIAN_LAPIS_ORE = register("ore_lapis", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_LAPIS_ORE.defaultBlockState(), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared());
	public static final ConfiguredFeature<?, ?> PERMIAN_EMERALD_ORE = register("ore_emerald", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_EMERALD_ORE.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	   
	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) 
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ModUtils.rL(id), configuredFeature);
	}
	
	static class ModBlockClusterFeatureConfig
	{
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PERMIAN_DESERT_SHRUB.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(9).build();
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_FERNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PERMIAN_DESERT_FERNS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(4).build();

		public static final BlockClusterFeatureConfig ARAUCARIA_STICKS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARAUCARIA_STICKS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		public static final BlockClusterFeatureConfig CONIFER_STICKS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CONIFER_STICKS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		public static final BlockClusterFeatureConfig GINKGO_STICKS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GINKGO_STICKS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		public static final BlockClusterFeatureConfig SCORCHED_STICKS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SCORCHED_STICKS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();

		public static final BlockClusterFeatureConfig GEYSER_BLOCK = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GEYSER_BLOCK.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		
		public static final BlockClusterFeatureConfig FERN_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

		public static final BlockClusterFeatureConfig ASHY_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ASHY_SHRUB.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	}
	
	public static void init() 
	{
		register("ash_layer", ASH_LAYER);
	}
}
