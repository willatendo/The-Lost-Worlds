package lostworlds.server.biome;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModConfiguredCarvers {
	public static final ConfiguredCarver<ProbabilityConfig> CAVE_CARVER = register("cave_carver", LostWorldsWorldCarvers.CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> CANYON_CARVER = register("canyon_carver", LostWorldsWorldCarvers.CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = register("underwater_cave_carver", LostWorldsWorldCarvers.UNDERWATER_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F)));
	public static final ConfiguredCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = register("underwater_canyon_carver", LostWorldsWorldCarvers.UNDERWATER_CANYON_CARVER.configured(new ProbabilityConfig(0.02F)));

	public static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String id, ConfiguredCarver<WC> configuredCarver) {
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, LostWorldsUtils.rL(id), configuredCarver);
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering: Mod Configured Carvers");
	}
}
