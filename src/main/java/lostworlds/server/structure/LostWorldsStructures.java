package lostworlds.server.structure;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsStructures {
	public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, LostWorldsUtils.ID);

	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> BLACK_MARKET = register("black_market", new BlackMarketStructure(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SURFACE_FOSSIL = register("surface_fossil", new SurfaceFossilStructure(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SUBTERRANEAN_FOSSIL = register("subterranean_fossil", new SubterraneanFossilStructure(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TRACE_FOSSIL = register("trace_fossil", new TraceFossilStructure(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> METEORITE = register("meteorite", new MeteoriteStructure(NoneFeatureConfiguration.CODEC));

	public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> register(String id, StructureFeature<NoneFeatureConfiguration> structure) {
//		StructureFeature.STRUCTURES_REGISTRY.put("lostworlds:" + id, structure);
		return STRUCTURE_FEATURES.register(id, () -> structure);
	}

	public static void deferred(IEventBus bus) {
		STRUCTURE_FEATURES.register(bus);
	}
}
