package lostworlds.server.structure;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class LostWorldsConfiguredStructures {
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_BLACK_MARKET = LostWorldsStructures.BLACK_MARKET.get().configured(FeatureConfiguration.NONE, LostWorldsTags.ModBiomeTags.HAS_BLACK_MARKET);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_SURFACE_FOSSIL = LostWorldsStructures.SURFACE_FOSSIL.get().configured(FeatureConfiguration.NONE, LostWorldsTags.ModBiomeTags.HAS_SURFACE_FOSSIL);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_SUBTERRANEAN_FOSSIL = LostWorldsStructures.SUBTERRANEAN_FOSSIL.get().configured(FeatureConfiguration.NONE, LostWorldsTags.ModBiomeTags.HAS_SUBTERRANEAN_FOSSIL);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_TRACE_FOSSIL = LostWorldsStructures.TRACE_FOSSIL.get().configured(FeatureConfiguration.NONE, LostWorldsTags.ModBiomeTags.HAS_TRACE_FOSSIL);
	public static ConfiguredStructureFeature<?, ?> CONFIGURED_METEORITE = LostWorldsStructures.METEORITE.get().configured(FeatureConfiguration.NONE, LostWorldsTags.ModBiomeTags.HAS_METEORITE);

	public static ConfiguredStructureFeature<?, ?> register(String id, ConfiguredStructureFeature<?, ?> structureFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, LostWorldsUtils.rL(id), structureFeature);
	}

	public static void init() {
		register("configured_black_market", CONFIGURED_BLACK_MARKET);
		register("configured_surface_fossil", CONFIGURED_SURFACE_FOSSIL);
		register("configured_subterranean_fossil", CONFIGURED_SUBTERRANEAN_FOSSIL);
		register("configured_trace_fossil", CONFIGURED_TRACE_FOSSIL);
		register("configured_meteorite", CONFIGURED_METEORITE);
	}
}
