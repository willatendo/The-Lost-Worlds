package lostworlds.server.structure;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LostWorldsConfiguredStructures {
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> BLACK_MARKET_KEY = createKey("black_market");
	public static final Holder<ConfiguredStructureFeature<?, ?>> CONFIGURED_BLACK_MARKET = register(BLACK_MARKET_KEY, LostWorldsStructures.BLACK_MARKET.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_BLACK_MARKET));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> SURFACE_FOSSIL_KEY = createKey("surface_fossil");
	public static final Holder<ConfiguredStructureFeature<?, ?>> CONFIGURED_SURFACE_FOSSIL = register(SURFACE_FOSSIL_KEY, LostWorldsStructures.SURFACE_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_SURFACE_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> SUBTERRANEAN_FOSSIL_KEY = createKey("subterranean_fossil");
	public static final Holder<ConfiguredStructureFeature<?, ?>> CONFIGURED_SUBTERRANEAN_FOSSIL = register(SUBTERRANEAN_FOSSIL_KEY, LostWorldsStructures.SUBTERRANEAN_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_SUBTERRANEAN_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> TRACE_FOSSIL_KEY = createKey("trace_fossil");
	public static final Holder<ConfiguredStructureFeature<?, ?>> CONFIGURED_TRACE_FOSSIL = register(TRACE_FOSSIL_KEY, LostWorldsStructures.TRACE_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_TRACE_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> METEORITE_KEY = createKey("meteorite");
	public static final Holder<ConfiguredStructureFeature<?, ?>> CONFIGURED_METEORITE = register(METEORITE_KEY, LostWorldsStructures.METEORITE.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_METEORITE));

	public static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> Holder<ConfiguredStructureFeature<?, ?>> register(ResourceKey<ConfiguredStructureFeature<?, ?>> p_211107_, ConfiguredStructureFeature<FC, F> p_211108_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_211107_, p_211108_);
	}

	private static ResourceKey<ConfiguredStructureFeature<?, ?>> createKey(String id) {
		return ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, LostWorldsUtils.rL(id));
	}

	public static void init() {
	}
}
