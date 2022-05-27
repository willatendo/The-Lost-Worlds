package lostworlds.server.structure;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

public class LostWorldsStructureSets {
	public static final ResourceKey<StructureSet> BLACK_MARKET_KEY = register("black_market");
	public static final Holder<StructureSet> BLACK_MARKET = register(BLACK_MARKET_KEY, LostWorldsConfiguredStructures.CONFIGURED_BLACK_MARKET, new RandomSpreadStructurePlacement(128, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.blackMarketGenerationId.get()));
	public static final ResourceKey<StructureSet> METEORITE_KEY = register("meteorite");
	public static final Holder<StructureSet> METEORITE = register(METEORITE_KEY, LostWorldsConfiguredStructures.CONFIGURED_METEORITE, new RandomSpreadStructurePlacement(128, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.meteoriteGenerationId.get()));
	public static final ResourceKey<StructureSet> SUBTERRANEAN_FOSSIL_KEY = register("subterranean_fossil");
	public static final Holder<StructureSet> SUBTERRANEAN_FOSSIL = register(SUBTERRANEAN_FOSSIL_KEY, LostWorldsConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.subterraneanFossilGenerationId.get()));
	public static final ResourceKey<StructureSet> SURFACE_FOSSIL_KEY = register("surface_fossil");
	public static final Holder<StructureSet> SURFACE_FOSSIL = register(SURFACE_FOSSIL_KEY, LostWorldsConfiguredStructures.CONFIGURED_SURFACE_FOSSIL, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.surfaceFossilGenerationId.get()));
	public static final ResourceKey<StructureSet> TRACE_FOSSIL_KEY = register("trace_fossil_key");
	public static final Holder<StructureSet> TRACE_FOSSIL = register(SURFACE_FOSSIL_KEY, LostWorldsConfiguredStructures.CONFIGURED_TRACE_FOSSIL, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.traceFossilGenerationId.get()));

	public static Holder<StructureSet> register(ResourceKey<StructureSet> key, StructureSet structureSet) {
		return BuiltinRegistries.register(BuiltinRegistries.STRUCTURE_SETS, key, structureSet);
	}

	public static Holder<StructureSet> register(ResourceKey<StructureSet> key, Holder<ConfiguredStructureFeature<?, ?>> feature, StructurePlacement placement) {
		return register(key, new StructureSet(feature, placement));
	}

	public static ResourceKey<StructureSet> register(String id) {
		return ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, LostWorldsUtils.rL(id));
	}

	public static void init() {
	}
}
