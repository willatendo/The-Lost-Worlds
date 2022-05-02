package lostworlds.server.structure;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsStructures {
	public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, LostWorldsUtils.ID);

	public static final Structure<NoFeatureConfig> BLACK_MARKET = register("black_market", new BlackMarketStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> SURFACE_FOSSIL = register("surface_fossil", new SurfaceFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> SUBTERRANEAN_FOSSIL = register("subterranean_fossil", new SubterraneanFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> TRACE_FOSSIL = register("trace_fossil", new TraceFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> METEORITE = register("meteorite", new MeteoriteStructure(NoFeatureConfig.CODEC));

	public static Structure<NoFeatureConfig> register(String id, Structure<NoFeatureConfig> structure) {
		STRUCTURE_FEATURES.register(id, () -> structure);
		return structure;
	}

	public static void deferred(IEventBus bus) {
		STRUCTURE_FEATURES.register(bus);
	}
}
