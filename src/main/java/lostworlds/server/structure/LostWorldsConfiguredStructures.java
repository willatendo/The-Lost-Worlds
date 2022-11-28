package lostworlds.server.structure;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsConfiguredStructures {
	public static final DeferredRegister<Structure> CONFIGURED_STRUCTURE_FEATURES = DeferredRegister.create(Registry.STRUCTURE_REGISTRY, LostWorldsUtils.ID);

	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> BLACK_MARKET_KEY = createKey("black_market");
	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_BLACK_MARKET = CONFIGURED_STRUCTURE_FEATURES.register("black_market", () -> LostWorldsStructures.BLACK_MARKET.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_BLACK_MARKET));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> SURFACE_FOSSIL_KEY = createKey("surface_fossil");
	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_SURFACE_FOSSIL = CONFIGURED_STRUCTURE_FEATURES.register("surface_fossil", () -> LostWorldsStructures.SURFACE_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_SURFACE_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> SUBTERRANEAN_FOSSIL_KEY = createKey("subterranean_fossil");
	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_SUBTERRANEAN_FOSSIL = CONFIGURED_STRUCTURE_FEATURES.register("subterranean_fossil", () -> LostWorldsStructures.SUBTERRANEAN_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_SUBTERRANEAN_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> TRACE_FOSSIL_KEY = createKey("trace_fossil");
	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_TRACE_FOSSIL = CONFIGURED_STRUCTURE_FEATURES.register("trace_fossil", () -> LostWorldsStructures.TRACE_FOSSIL.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_TRACE_FOSSIL));
	public static final ResourceKey<ConfiguredStructureFeature<?, ?>> METEORITE_KEY = createKey("meteorite");
	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_METEORITE = CONFIGURED_STRUCTURE_FEATURES.register("meteorite", () -> LostWorldsStructures.METEORITE.get().configured(NoneFeatureConfiguration.INSTANCE, LostWorldsTags.ModBiomeTags.HAS_METEORITE));

	private static ResourceKey<ConfiguredStructureFeature<?, ?>> createKey(String id) {
		return ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, LostWorldsUtils.rL(id));
	}
}
