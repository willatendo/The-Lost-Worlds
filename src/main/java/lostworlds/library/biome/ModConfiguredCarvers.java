package lostworlds.library.biome;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.WorldCarverInit;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModConfiguredCarvers {
	public static final ConfiguredCarver<ProbabilityConfig> CAVE_CARVER = ModRegistry.register("cave_carver", WorldCarverInit.CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> CANYON_CARVER = ModRegistry.register("canyon_carver", WorldCarverInit.CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = ModRegistry.register("underwater_cave_carver", WorldCarverInit.UNDERWATER_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = ModRegistry.register("underwater_canyon_carver", WorldCarverInit.UNDERWATER_CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static void init() {
		ModUtils.LOGGER.debug("Registering: Mod Configured Carvers");
	}
}
