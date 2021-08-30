package lostworlds.library.biome;

import lostworlds.content.server.init.WorldCarverInit;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModConfiguredCarvers 
{
	public static final ConfiguredCarver<ProbabilityConfig> PERMIAN_CAVE_CARVER = ModRegistry.register("permian_cave_carver", WorldCarverInit.PERMIAN_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> PERMIAN_CANYON_CARVER = ModRegistry.register("permian_canyon_carver", WorldCarverInit.PERMIAN_CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));
	
	public static final ConfiguredCarver<ProbabilityConfig> PERMIAN_UNDERWATER_CAVE_CARVER = ModRegistry.register("permian_underwater_cave_carver", WorldCarverInit.PERMIAN_UNDERWATER_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> PERMIAN_UNDERWATER_CANYON_CARVER = ModRegistry.register("permian_underwater_canyon_carver", WorldCarverInit.PERMIAN_UNDERWATER_CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));
	
	public static void init() { ModUtils.LOGGER.debug("Registering: Mod Configured Carvers"); }
}
