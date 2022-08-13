package lostworlds.server.biome.features.placed;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsPlacedFeatures {
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, LostWorldsUtils.ID);

	// Misc Decoration
	public static final RegistryObject<PlacedFeature> PERMIAN_ROCK = register("permian_rock", LostWorldsConfiguredFeatures.PERMIAN_ROCK, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> JURASSIC_ROCK = register("jurassic_rock", LostWorldsConfiguredFeatures.JURASSIC_ROCK, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsConfiguredFeatures.CRETACEOUS_ROCK, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> ASH_LAYER = register("ash_layer", LostWorldsConfiguredFeatures.ASH_LAYER, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> GEYSER_BLOCK = register("geyser_block", LostWorldsConfiguredFeatures.GEYSER_BLOCK, CountPlacement.of(5), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> SPONGE_COLONEY = register("sponge_coloney", LostWorldsConfiguredFeatures.SPONGE_COLONEY, CountPlacement.of(5), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

	public static RegistryObject<PlacedFeature> register(String id, RegistryObject<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return PLACED_FEATURES.register(id, () -> new PlacedFeature(feature.getHolder().get(), List.of(modifiers)));
	}

	public static RegistryObject<PlacedFeature> register(String id, RegistryObject<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
		return PLACED_FEATURES.register(id, () -> new PlacedFeature(feature.getHolder().get(), modifiers));
	}

	public static void init() {
		PlacedDisksFeatures.init();
		PlacedOreFeatures.init();
		PlacedPlantPatchFeatures.init();
		PlacedTreeFeatures.init();
		PlacedTreePatchFeatures.init();
		PlacedWaterFeatures.init();
	}
}
