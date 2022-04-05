package lostworlds.server.biome;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.structure.LostWorldsStructures;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {
	public static StructureFeature<?, ?> CONFIGURED_BLACK_MARKET = LostWorldsStructures.BLACK_MARKET.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_SURFACE_FOSSIL = LostWorldsStructures.SURFACE_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_SUBTERRANEAN_FOSSIL = LostWorldsStructures.SUBTERRANEAN_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_TRACE_FOSSIL = LostWorldsStructures.TRACE_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_METEORITE = LostWorldsStructures.METEORITE.configured(IFeatureConfig.NONE);

	public static StructureFeature<?, ?> register(String id, StructureFeature<?, ?> structureFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, LostWorldsUtils.rL(id), structureFeature);
	}

	public static void init() {
		register("configured_black_market", CONFIGURED_BLACK_MARKET);
		register("configured_surface_fossil", CONFIGURED_SURFACE_FOSSIL);
		register("configured_subterranean_fossil", CONFIGURED_SUBTERRANEAN_FOSSIL);
		register("configured_trace_fossil", CONFIGURED_TRACE_FOSSIL);
		register("configured_meteorite", CONFIGURED_METEORITE);

		FlatGenerationSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.BLACK_MARKET, CONFIGURED_BLACK_MARKET);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.SURFACE_FOSSIL, CONFIGURED_SURFACE_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.SUBTERRANEAN_FOSSIL, CONFIGURED_SUBTERRANEAN_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.TRACE_FOSSIL, CONFIGURED_TRACE_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(LostWorldsStructures.METEORITE, CONFIGURED_METEORITE);
	}
}
