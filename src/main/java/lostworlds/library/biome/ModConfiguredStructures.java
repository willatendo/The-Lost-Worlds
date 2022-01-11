package lostworlds.library.biome;

import lostworlds.content.ModRegistry;
import lostworlds.content.server.init.StructureInit;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {
	public static StructureFeature<?, ?> CONFIGURED_BLACK_MARKET = StructureInit.BLACK_MARKET.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_SURFACE_FOSSIL = StructureInit.SURFACE_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_SUBTERRANEAN_FOSSIL = StructureInit.SUBTERRANEAN_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_TRACE_FOSSIL = StructureInit.TRACE_FOSSIL.configured(IFeatureConfig.NONE);
	public static StructureFeature<?, ?> CONFIGURED_METEORITE = StructureInit.METEORITE.configured(IFeatureConfig.NONE);

	public static void init() {
		ModRegistry.register("configured_black_market", CONFIGURED_BLACK_MARKET);
		ModRegistry.register("configured_surface_fossil", CONFIGURED_SURFACE_FOSSIL);
		ModRegistry.register("configured_subterranean_fossil", CONFIGURED_SUBTERRANEAN_FOSSIL);
		ModRegistry.register("configured_trace_fossil", CONFIGURED_TRACE_FOSSIL);
		ModRegistry.register("configured_meteorite", CONFIGURED_METEORITE);

		FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.BLACK_MARKET, CONFIGURED_BLACK_MARKET);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.SURFACE_FOSSIL, CONFIGURED_SURFACE_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.SUBTERRANEAN_FOSSIL, CONFIGURED_SUBTERRANEAN_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.TRACE_FOSSIL, CONFIGURED_TRACE_FOSSIL);
		FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.METEORITE, CONFIGURED_METEORITE);
	}
}
