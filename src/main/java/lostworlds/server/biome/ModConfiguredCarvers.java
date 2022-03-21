package lostworlds.server.biome;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModConfiguredCarvers {
	public static final ConfiguredCarver<ProbabilityConfig> CAVE_CARVER = LostWorldsRegistry.register("cave_carver", LostWorldsWorldCarvers.CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> CANYON_CARVER = LostWorldsRegistry.register("canyon_carver", LostWorldsWorldCarvers.CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = LostWorldsRegistry.register("underwater_cave_carver", LostWorldsWorldCarvers.UNDERWATER_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = LostWorldsRegistry.register("underwater_canyon_carver", LostWorldsWorldCarvers.UNDERWATER_CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering: Mod Configured Carvers");
	}
}
