package lostworlds.server.structure;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class LostWorldsStructures {
	public static final Structure<NoFeatureConfig> BLACK_MARKET = LostWorldsRegistry.register("black_market", new BlackMarketStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> SURFACE_FOSSIL = LostWorldsRegistry.register("surface_fossil", new SurfaceFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> SUBTERRANEAN_FOSSIL = LostWorldsRegistry.register("subterranean_fossil", new SubterraneanFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> TRACE_FOSSIL = LostWorldsRegistry.register("trace_fossil", new TraceFossilStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> METEORITE = LostWorldsRegistry.register("meteorite", new MeteoriteStructure(NoFeatureConfig.CODEC));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Structures");
	}
}
