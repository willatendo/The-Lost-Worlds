package lostworlds.server.structure;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsStructures {
	public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, LostWorldsUtils.ID);

	public static final RegistryObject<Structure<NoFeatureConfig>> BLACK_MARKET = register("black_market", new BlackMarketStructure(NoFeatureConfig.CODEC));
	public static final RegistryObject<Structure<NoFeatureConfig>> SURFACE_FOSSIL = register("surface_fossil", new SurfaceFossilStructure(NoFeatureConfig.CODEC));
	public static final RegistryObject<Structure<NoFeatureConfig>> SUBTERRANEAN_FOSSIL = register("subterranean_fossil", new SubterraneanFossilStructure(NoFeatureConfig.CODEC));
	public static final RegistryObject<Structure<NoFeatureConfig>> TRACE_FOSSIL = register("trace_fossil", new TraceFossilStructure(NoFeatureConfig.CODEC));
	public static final RegistryObject<Structure<NoFeatureConfig>> METEORITE = register("meteorite", new MeteoriteStructure(NoFeatureConfig.CODEC));

	public static RegistryObject<Structure<NoFeatureConfig>> register(String id, Structure<NoFeatureConfig> structure) {
		Structure.STRUCTURES_REGISTRY.put("lostworlds:" + id, structure);
		return STRUCTURE_FEATURES.register(id, () -> structure);
	}

	public static void deferred(IEventBus bus) {
		STRUCTURE_FEATURES.register(bus);
	}
}
