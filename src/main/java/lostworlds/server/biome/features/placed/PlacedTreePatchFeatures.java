package lostworlds.server.biome.features.placed;

import static lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures.register;

import lostworlds.server.biome.features.configured.TreePatchFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class PlacedTreePatchFeatures {
	public static final Holder<PlacedFeature> SCANT_ARCTIC_CONIFER_TREES = register("scant_arctic_conifer_trees", TreePatchFeatures.ARCTIC_CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> SCANT_FROZEN_TREES = register("scant_frozen_trees", TreePatchFeatures.FROZEN_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_FROZEN_TREES = register("sparse_frozen_trees", TreePatchFeatures.FROZEN_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_BROKEN_TREES = register("sparse_broken_trees", TreePatchFeatures.ARCTIC_CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> CALAMITES_TREES = register("calamites_trees", TreePatchFeatures.CALAMITES_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> CONIFER_TREES = register("calamities_trees", TreePatchFeatures.CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> SCANT_CONIFER_TREES = register("scant_calamities_trees", TreePatchFeatures.CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_CONIFER_TREES = register("sparse_calamities_trees", TreePatchFeatures.CONIFER_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> SCANT_CYPRESS_TREES = register("scant_cypress_trees", TreePatchFeatures.CYPRESS_TREES, PlacementUtils.countExtra(5, 0.1F, 1), SurfaceWaterDepthFilter.forMaxDepth(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().getDefaultState(), BlockPos.ZERO)));
	public static final Holder<PlacedFeature> SPARSE_CYPRESS_TREES = register("sparse_cypress_trees", TreePatchFeatures.CYPRESS_TREES, PlacementUtils.countExtra(0, 0.1F, 1), SurfaceWaterDepthFilter.forMaxDepth(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().getDefaultState(), BlockPos.ZERO)));
	public static final Holder<PlacedFeature> GINKGO_TREES = register("ginkgo_trees", TreePatchFeatures.GINKGO_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_BABY_SEQUOIA_TREES = register("sparse_baby_sequoia_trees", TreePatchFeatures.BABY_SEQUOIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_SEQUOIA_TREES = register("sparse_sequoia_trees", TreePatchFeatures.SEQUOIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> ARAUCARIA_TREES = register("araucaria_trees", TreePatchFeatures.ARAUCARIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(50, 0.1F, 1)));
	public static final Holder<PlacedFeature> SPARSE_ARAUCARIA_TREES = register("araucaria_trees", TreePatchFeatures.ARAUCARIA_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> SINGLE_SCORCHED_TREE = register("single_scorched_tree", TreePatchFeatures.SCORCHED_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05F, 1)));

	public static void init() {
	}
}
