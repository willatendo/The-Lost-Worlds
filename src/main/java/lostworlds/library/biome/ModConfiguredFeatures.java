package lostworlds.library.biome;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import lostworlds.content.ModUtils;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.library.block.Damage;
import lostworlds.library.block.PlantFossilBlock;
import lostworlds.library.block.Plants;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.foliageplacer.ConiferFoliagePlacer;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.SingleRandomFeature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures 
{
	//Plants
	public static final ConfiguredFeature<?, ?> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BRAZILEA.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	public static final ConfiguredFeature<?, ?> CALAMITES_SUCKOWII = register("calamites_suckwii", FeatureInit.CALAMITES_SUCKOWII.configured(new ProbabilityConfig(0.2F)).decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D))));
	public static final ConfiguredFeature<?, ?> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH.configured(ModConfiguredFeatures.ModBlockClusterFeatureConfig.FERN_CONFIG));	
	public static final ConfiguredFeature<?, ?> WILLIAMSONIA_PATCH = register("williamsonia_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.WILLIAMSONIA.defaultBlockState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> ZAMITES = register("zamites", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ZAMITES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	//Trees
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CALAMITIES_TREE = register("calamties_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CALAMITES_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.CALAMITES_LEAVES.defaultBlockState()), new ConiferFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(15, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CALAMITIES_TREES = register("calamties_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CALAMITIES_TREE.weighted(0.33333334F)), CALAMITIES_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CONIFER_TREE = register("conifer_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CONIFER_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.CONIFER_LEAVES.defaultBlockState()), new ConiferFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2), FeatureSpread.of(1, 1)), new StraightTrunkPlacer(5, 2, 1), new TwoLayerFeature(2, 0, 2))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> CONIFER_TREES = register("conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SCANT_CONIFER_TREES = register("scant_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES = register("sparse_conifer_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CONIFER_TREE.weighted(0.33333334F)), CONIFER_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> CONIFER_STICKS = register("conifer_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CONIFER_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GINKGO_TREE = register("ginkgo_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.GINKGO_LOG.defaultBlockState()), new SimpleBlockStateProvider(BlockInit.GINKGO_LEAVES.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> GINKGO_TREES = register("ginkgo_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GINKGO_TREE.weighted(0.33333334F)), GINKGO_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> GINKGO_STICKS = register("ginkgo_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CONIFER_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> GINKGO_FOREST_TREES = register("ginkgo_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.OAK.weighted(0.33333334F), Features.FANCY_OAK.weighted(0.1F)), Features.OAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.1F, 1))));
	
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SCORCHED_TREE = register("scorched_tree", FeatureInit.SCORCHED_TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SCORCHED_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<?, ?> SINGLE_SCORCHED_TREE = register("single_scorched_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ModConfiguredFeatures.SCORCHED_TREE.weighted(0.33333334F)), ModConfiguredFeatures.SCORCHED_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
	public static final ConfiguredFeature<?, ?> SCORCHED_STICKS = register("ginkgo_sticks", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.SCORCHED_STICKS).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	//Ores
	public static final ConfiguredFeature<?, ?> COPPER_ORE = register("copper_ore", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.COPPER_ORE.defaultBlockState(), LostWorldsConfig.COMMON_CONFIG.copperVeinSize.get())).range(LostWorldsConfig.COMMON_CONFIG.copperRange.get()).squared().count(LostWorldsConfig.COMMON_CONFIG.copperCountPerChunk.get()));
	public static final ConfiguredFeature<?, ?> SILT_PATCH = register("silt_patch", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.SILT.defaultBlockState(), LostWorldsConfig.COMMON_CONFIG.siltVeinSize.get())).range(LostWorldsConfig.COMMON_CONFIG.siltRange.get()).squared().count(LostWorldsConfig.COMMON_CONFIG.siltCountPerChunk.get()));

	public static final ConfiguredFeature<?, ?> PETRIFIED_ARAUCARIA = register("petrified_araucaria", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PETRIFIED_ARAUCARIA_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> PETRIFIED_CALAMITES = register("petrified_calamties", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PETRIFIED_CALAMITES_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> PETRIFIED_CONIFER = register("petrified_conifer", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PETRIFIED_CONIFER_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> PETRIFIED_GINKGO = register("petrified_ginkgo", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PETRIFIED_GINKGO_LOG.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	
	public static final ConfiguredFeature<?, ?> BASALT_DIAMOND_ORE = register("basalt_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModFillerBlockTypes.BASALT, BlockInit.BASALT_DIAMOND_ORE.defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(40, 0, 100))).squared().count(30));

	public static final ConfiguredFeature<?, ?> PERMIAN_COAL_ORE = register("permian_coal_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_COAL_ORE.defaultBlockState(), 17)).range(128).squared().count(20));
	public static final ConfiguredFeature<?, ?> PERMIAN_IRON_ORE = register("permian_iron_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_IRON_ORE.defaultBlockState(), 9)).range(64).squared().count(20));
	public static final ConfiguredFeature<?, ?> PERMIAN_GOLD_ORE = register("permian_gold_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_GOLD_ORE.defaultBlockState(), 9)).range(32).squared().count(2));
	public static final ConfiguredFeature<?, ?> PERMIAN_REDSTONE_ORE = register("permian_redstone_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_REDSTONE_ORE.defaultBlockState(), 8)).range(16).squared().count(8));
	public static final ConfiguredFeature<?, ?> PERMIAN_DIAMOND_ORE = register("permian_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_DIAMOND_ORE.defaultBlockState(), 8)).range(16).squared());
	public static final ConfiguredFeature<?, ?> PERMIAN_LAPIS_ORE = register("permian_lapis_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_LAPIS_ORE.defaultBlockState(), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared());
	public static final ConfiguredFeature<?, ?> PERMIAN_EMERALD_ORE = register("permian_emerald_ore", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_EMERALD_ORE.defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> PERMIAN_COPPER_ORE = register("permian_copper_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, BlockInit.PERMIAN_COPPER_ORE.defaultBlockState(), LostWorldsConfig.COMMON_CONFIG.copperVeinSize.get())).range(LostWorldsConfig.COMMON_CONFIG.copperRange.get()).squared().count(LostWorldsConfig.COMMON_CONFIG.copperCountPerChunk.get()));

	public static final ConfiguredFeature<?, ?> PERMIAN_DIRT_ORE = register("permian_dirt_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, Blocks.DIRT.defaultBlockState(), 33)).range(256).squared().count(10));
	public static final ConfiguredFeature<?, ?> PERMIAN_GRAVEL_ORE = register("permian_gravel_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE, Blocks.GRAVEL.defaultBlockState(), 33)).range(256).squared().count(8));
	
	//Rocks
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", FeatureInit.PERMIAN_ROCK.configured(new BlockStateFeatureConfig(BlockInit.PERMIAN_COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));		
	
	//Lakes
	public static final ConfiguredFeature<?, ?> PERMIAN_WATER_LAKE = register("permian_water_lake", FeatureInit.PERMIAN_LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final ConfiguredFeature<?, ?> PERMIAN_LAVA_LAKE = register("permian_lava_lake", FeatureInit.PERMIAN_LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));
		
	public static final ConfiguredFeature<?, ?> ANCIENT_SPRING = register("ancient_spring", Feature.SPRING.configured(new LiquidsConfig(Fluids.WATER.defaultFluidState(), true, 4, 1, ImmutableSet.of(BlockInit.PERMIAN_STONE))).decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));

	//Fossils
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS = register("overworld_plant_fossil_alethopteris", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_BRAZILEA = register("overworld_plant_fossil_brazilea", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.BRAZILEA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII = register("overworld_plant_fossil_calamites_suckowii", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CALAMITES_SUCKOWII).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS = register("overworld_plant_fossil_cephalotaxus", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CEPHALOTAXUS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_DILLHOFFIA = register("overworld_plant_fossil_dillhoffia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DILLHOFFIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_DUISBERGIA = register("overworld_plant_fossil_duisbergia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DUISBERGIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_OSMUNDA = register("overworld_plant_fossil_osmunda", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.OSMUNDA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA = register("overworld_plant_fossil_williamsonia", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.WILLIAMSONIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final ConfiguredFeature<?, ?> OVERWORLD_PLANT_FOSSIL_ZAMITES = register("overworld_plant_fossil_zamites", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), BlockInit.PLANT_FOSSIL.defaultBlockState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ZAMITES).setValue(PlantFossilBlock.DAMAGE, Damage.NONE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	
	//Misc Decoration
	public static final ConfiguredFeature<?, ?> ASH_LAYER = FeatureInit.ASH_LAYER_PLACEMENT.configured(IFeatureConfig.NONE);
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
	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", FeatureInit.GEYSER_BLOCK_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	public static final ConfiguredFeature<?, ?> MUD_DISK = register("mud_disk", Feature.DISK.configured(new SphereReplaceConfig(BlockInit.MUD.defaultBlockState(), FeatureSpread.of(2, 1), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), BlockInit.MUD.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", FeatureInit.SPONGE_COLONEY_PLACEMENT.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

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

		public static final BlockClusterFeatureConfig ZAMITES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ZAMITES.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	}
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Configured Features");
		
		register("ash_layer", ASH_LAYER);
	}
}
