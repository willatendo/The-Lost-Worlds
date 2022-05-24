package lostworlds.server.biome.features.placed;

import java.util.List;

import lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class LostWorldsPlacedFeatures {
	// Misc Decoration
	public static final Holder<PlacedFeature> PERMIAN_ROCK = register("permian_rock", LostWorldsConfiguredFeatures.PERMIAN_ROCK, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> JURASSIC_ROCK = register("jurassic_rock", LostWorldsConfiguredFeatures.JURASSIC_ROCK, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsConfiguredFeatures.CRETACEOUS_ROCK, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> ASH_LAYER = PlacementUtils.register("ash_layer", LostWorldsConfiguredFeatures.ASH_LAYER, BiomeFilter.biome());
	public static final Holder<PlacedFeature> GEYSER_BLOCK = PlacementUtils.register("geyser_block", LostWorldsConfiguredFeatures.GEYSER_BLOCK, CountPlacement.of(16), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> SPONGE_COLONEY = PlacementUtils.register("sponge_coloney", LostWorldsConfiguredFeatures.SPONGE_COLONEY, CountPlacement.of(16), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

	public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return PlacementUtils.register("lostworlds:" + id, feature, modifiers);
	}

	public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
		return PlacementUtils.register("lostworlds:" + id, feature, modifiers);
	}

	public static void init() {
		PlacedDisksFeatures.init();
		PlacedOreFeatures.init();
		PlacedPlantPatchFeatures.init();
	}
}
