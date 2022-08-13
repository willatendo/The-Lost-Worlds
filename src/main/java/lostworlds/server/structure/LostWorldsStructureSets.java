package lostworlds.server.structure;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsStructureSets {
	public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.create(Registry.STRUCTURE_SET_REGISTRY, LostWorldsUtils.ID);

	public static final RegistryObject<StructureSet> BLACK_MARKET = STRUCTURE_SETS.register("black_market", () -> new StructureSet(LostWorldsConfiguredStructures.CONFIGURED_BLACK_MARKET.getHolder().get(), new RandomSpreadStructurePlacement(128, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.blackMarketGenerationId.get())));
	public static final RegistryObject<StructureSet> METEORITE = STRUCTURE_SETS.register("meteorite", () -> new StructureSet(LostWorldsConfiguredStructures.CONFIGURED_METEORITE.getHolder().get(), new RandomSpreadStructurePlacement(128, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.meteoriteGenerationId.get())));
	public static final RegistryObject<StructureSet> SUBTERRANEAN_FOSSIL = STRUCTURE_SETS.register("subterranean_fossil", () -> new StructureSet(LostWorldsConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL.getHolder().get(), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.subterraneanFossilGenerationId.get())));
	public static final RegistryObject<StructureSet> SURFACE_FOSSIL = STRUCTURE_SETS.register("surface_fossil", () -> new StructureSet(LostWorldsConfiguredStructures.CONFIGURED_SURFACE_FOSSIL.getHolder().get(), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.surfaceFossilGenerationId.get())));
	public static final RegistryObject<StructureSet> TRACE_FOSSIL = STRUCTURE_SETS.register("trace_fossil", () -> new StructureSet(LostWorldsConfiguredStructures.CONFIGURED_TRACE_FOSSIL.getHolder().get(), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, LostWorldsConfig.COMMON_CONFIG.traceFossilGenerationId.get())));
}
