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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class PlacedTreeFeatures {
	// Predicates
	public static final BlockPredicate SNOW_TREE_PREDICATE = BlockPredicate.matchesBlocks(List.of(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW), new BlockPos(0, -1, 0));
	public static final List<PlacementModifier> SNOW_TREE_FILTER_DECORATOR = List.of(EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlock(Blocks.POWDER_SNOW, BlockPos.ZERO)), 8), BlockPredicateFilter.forPredicate(SNOW_TREE_PREDICATE));
	public static final BlockPredicate ASH_TREE_PREDICATE = BlockPredicate.matchesBlocks(List.of(LostWorldsBlocks.VOLCANIC_ASH.get()), new BlockPos(0, -1, 0));
	public static final List<PlacementModifier> ASH_TREE_FILTER_DECORATOR = List.of(EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.not(BlockPredicate.matchesBlock(LostWorldsBlocks.VOLCANIC_ASH.get(), BlockPos.ZERO)), 8), BlockPredicateFilter.forPredicate(SNOW_TREE_PREDICATE));

	public static final Holder<PlacedFeature> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", TreeFeatures.ARCTIC_CONIFER_TREE, SNOW_TREE_FILTER_DECORATOR);
	public static final Holder<PlacedFeature> FROZEN_TREE = register("frozen_tree", TreeFeatures.FROZEN_TREE, SNOW_TREE_FILTER_DECORATOR);
	public static final Holder<PlacedFeature> BROKEN_TREE = register("broken_tree", TreeFeatures.BROKEN_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
	public static final Holder<PlacedFeature> CALAMITES_TREE = register("calamites_tree", TreeFeatures.CALAMITES_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> CONIFER_TREE = register("conifer_tree", TreeFeatures.CONIFER_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> CYPRESS_TREE = register("cypress_tree", TreeFeatures.CYPRESS_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> GINKGO_TREE = register("ginkgo_tree", TreeFeatures.GINKGO_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", TreeFeatures.BABY_SEQUOIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> SEQUOIA_TREE = register("sequoia_tree", TreeFeatures.SEQUOIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> ARAUCARIA_TREE = register("araucaria_tree", TreeFeatures.ARAUCARIA_TREE, PlacementUtils.filteredByBlockSurvival(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.SAPLING).get().get()));
	public static final Holder<PlacedFeature> SCORCHED_TREE = register("scorched_tree", TreeFeatures.SCORCHED_TREE, ASH_TREE_FILTER_DECORATOR);

	public static void init() {
	}
}
