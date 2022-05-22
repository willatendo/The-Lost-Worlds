package lostworlds.server.biome;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.structure.LostWorldsStructures;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class ModConfiguredStructures {
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_BLACK_MARKET = LostWorldsStructures.BLACK_MARKET.get().configured(FeatureConfiguration.NONE);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_SURFACE_FOSSIL = LostWorldsStructures.SURFACE_FOSSIL.get().configured(FeatureConfiguration.NONE);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_SUBTERRANEAN_FOSSIL = LostWorldsStructures.SUBTERRANEAN_FOSSIL.get().configured(FeatureConfiguration.NONE);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_TRACE_FOSSIL = LostWorldsStructures.TRACE_FOSSIL.get().configured(FeatureConfiguration.NONE);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_METEORITE = LostWorldsStructures.METEORITE.get().configured(FeatureConfiguration.NONE);

	public static ConfiguredStructureFeature<?, ?> register(String id, ConfiguredStructureFeature<?, ?> structureFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, LostWorldsUtils.rL(id), structureFeature);
	}

	public static void init() {
		register("configured_black_market", CONFIGURED_BLACK_MARKET);
		register("configured_surface_fossil", CONFIGURED_SURFACE_FOSSIL);
		register("configured_subterranean_fossil", CONFIGURED_SUBTERRANEAN_FOSSIL);
		register("configured_trace_fossil", CONFIGURED_TRACE_FOSSIL);
		register("configured_meteorite", CONFIGURED_METEORITE);

		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.BLACK_MARKET.get(), CONFIGURED_BLACK_MARKET);
		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.SURFACE_FOSSIL.get(), CONFIGURED_SURFACE_FOSSIL);
		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.SUBTERRANEAN_FOSSIL.get(), CONFIGURED_SUBTERRANEAN_FOSSIL);
		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.TRACE_FOSSIL.get(), CONFIGURED_TRACE_FOSSIL);
		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.METEORITE.get(), CONFIGURED_METEORITE);
	}
}
