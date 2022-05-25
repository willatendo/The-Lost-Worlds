package lostworlds.server.biome.features.placed;

import static lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures.register;

import java.util.List;

import lostworlds.server.biome.features.configured.TreeFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class PlacedTreeFeatures {
	// Predicates
	public static final BlockPredicate SNOW_TREE_PREDICATE = BlockPredicate.matchesBlocks(List.of(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW), new BlockPos(0, -1, 0));
	public static final List<PlacementModifier> SNOW_TREE_FILTER_DECORATOR = List.of(EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlock(Blocks.POWDER_SNOW, BlockPos.ZERO)), 8), BlockPredicateFilter.forPredicate(SNOW_TREE_PREDICATE));
	public static final BlockPredicate ASH_TREE_PREDICATE = BlockPredicate.matchesBlocks(List.of(LostWorldsBlocks.VOLCANIC_ASH.get()), new BlockPos(0, -1, 0));
	public static final List<PlacementModifier> ASH_TREE_FILTER_DECORATOR = List.of(EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlock(LostWorldsBlocks.VOLCANIC_ASH.get(), BlockPos.ZERO)), 8), BlockPredicateFilter.forPredicate(SNOW_TREE_PREDICATE));

	public static final Holder<PlacedFeature> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", TreeFeatures.ARCTIC_CONIFER_TREE, SNOW_TREE_FILTER_DECORATOR);
	public static final Holder<PlacedFeature> SCANT_ARCTIC_CONIFER_TREES = register("scant_arctic_conifer_trees", TreeFeatures.ARCTIC_CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> FROZEN_TREE = register("frozen_tree", TreeFeatures.FROZEN_TREE, SNOW_TREE_FILTER_DECORATOR);
	public static final Holder<PlacedFeature> SCANT_FROZEN_TREES = register("scant_frozen_trees", TreeFeatures.FROZEN_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_FROZEN_TREES = register("sparse_frozen_trees", TreeFeatures.FROZEN_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> BROKEN_TREE = register("broken_tree", TreeFeatures.BROKEN_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
	public static final Holder<PlacedFeature> SPARSE_BROKEN_TREES = register("sparse_broken_trees", TreeFeatures.ARCTIC_CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> CALAMITES_TREE = register("calamites_tree", TreeFeatures.CALAMITES_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> CALAMITES_TREES = register("calamites_trees", TreeFeatures.CALAMITES_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> CONIFER_TREE = register("conifer_tree", TreeFeatures.CONIFER_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> CONIFER_TREES = register("calamities_trees", TreeFeatures.CALAMITES_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> SCANT_CONIFER_TREES = register("scant_calamities_trees", TreeFeatures.CALAMITES_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_CONIFER_TREES = register("sparse_calamities_trees", TreeFeatures.CALAMITES_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> CYPRESS_TREE = register("cypress_tree", TreeFeatures.CYPRESS_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> SCANT_CYPRESS_TREES = register("scant_cypress_trees", TreeFeatures.CYPRESS_TREES, PlacementUtils.countExtra(5, 0.1F, 1), SurfaceWaterDepthFilter.forMaxDepth(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().getDefaultState(), BlockPos.ZERO)));
	public static final Holder<PlacedFeature> SPARSE_CYPRESS_TREES = register("sparse_cypress_trees", TreeFeatures.CYPRESS_TREES, PlacementUtils.countExtra(0, 0.1F, 1), SurfaceWaterDepthFilter.forMaxDepth(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().getDefaultState(), BlockPos.ZERO)));
	public static final Holder<PlacedFeature> GINKGO_TREE = register("ginkgo_tree", TreeFeatures.GINKGO_TREE);
	public static final Holder<PlacedFeature> GINKGO_TREES = register("ginkgo_trees", TreeFeatures.GINKGO_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", TreeFeatures.BABY_SEQUOIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> SPARSE_BABY_SEQUOIA_TREES = register("sparse_baby_sequoia_trees", TreeFeatures.BABY_SEQUOIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> SEQUOIA_TREE = register("sequoia_tree", TreeFeatures.SEQUOIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> SPARSE_SEQUOIA_TREES = register("sparse_sequoia_trees", TreeFeatures.SEQUOIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> ARAUCARIA_TREE = register("araucaria_tree", TreeFeatures.ARAUCARIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> ARAUCARIA_TREES = register("araucaria_trees", TreeFeatures.ARAUCARIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> SCORCHED_TREE = register("scorched_tree", TreeFeatures.SCORCHED_TREE, ASH_TREE_FILTER_DECORATOR);
	public static final Holder<PlacedFeature> SINGLE_SCORCHED_TREE = register("single_scorched_tree", TreeFeatures.SCORCHED_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05F, 1)));
}
