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
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures 
{
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", FeatureInit.PERMIAN_ROCK.configured(new BlockStateFeatureConfig(BlockInit.PERMIAN_COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	
	public static final ConfiguredFeature<?, ?> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH.configured(ModConfiguredFeatures.ModBlockClusterFeatureConfig.FERN_CONFIG));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> CONIFER_STICKS = register("conifer_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CONIFER_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	
	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", FeatureInit.GEYSER_BLOCK_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", Feature.SIMPLE_BLOCK.configured(new BlockWithContextConfig(BlockInit.SPONGE_COLONY.defaultBlockState(), ImmutableList.of(BlockInit.PERMIAN_SAND.defaultBlockState()), ImmutableList.of(Blocks.WATER.defaultBlockState()), ImmutableList.of(Blocks.WATER.defaultBlockState()))).decorated(Placement.CARVING_MASK.configured(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.CONIFER_LEAVES.defaultBlockState()), new ConiferFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GINKGO_TREE = register("ginkgo_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GINKGO_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.GINKGO_LEAVES.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SCORCHED_TREE = register("scorched_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SCORCHED_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

	public static final ConfiguredFeature<?, ?> TREES_CONIFER = register("trees_conifer", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> TREES_GINKGO = register("trees_ginkgo", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ModConfiguredFeatures.SCORCHED_TREE.weighted(0.5F)), ModConfiguredFeatures.SCORCHED_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
	
	public static final ConfiguredFeature<?, ?> ASHY_SHRUB = register("ashy_shrub", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ASHY_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> ASH_LAYER = FeatureInit.ASH_LAYER_PLACEMENT.configured(IFeatureConfig.NONE);
	
	public static final ConfiguredFeature<?, ?> ORE_BASALT_DIAMOND = register("ore_basalt_diamond", Feature.ORE.configured(new OreFeatureConfig(ModFillerBlockTypes.BASALT, BlockInit.BASALT_DIAMOND_ORE.defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(40, 0, 100))).squared().count(30));
	
	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) 
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ModUtils.rL(id), configuredFeature);
	}
	
	static class ModBlockClusterFeatureConfig
	{
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PERMIAN_DESERT_SHRUB.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(9).build();
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_FERNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PERMIAN_DESERT_FERNS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(4).build();

		public static final BlockClusterFeatureConfig CONIFER_STICKS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CONIFER_STICKS.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		public static final BlockClusterFeatureConfig GEYSER_BLOCK = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GEYSER_BLOCK.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();
		
		public static final BlockClusterFeatureConfig FERN_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

		public static final BlockClusterFeatureConfig ASHY_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ASHY_SHRUB.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	}
	
	public static void init() 
	{
		register("ash_layer", ASH_LAYER);
	}
}
