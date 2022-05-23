package lostworlds.server.dimension.carver;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class LostWorldsConfiguredCarvers {
	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> CAVE_CARVER = register("cave_carver", LostWorldsWorldCarvers.CAVE_CARVER.configured(new ProbabilityFeatureConfiguration(0.14285715F)));
	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> CANYON_CARVER = register("canyon_carver", LostWorldsWorldCarvers.CANYON_CARVER.configured(new ProbabilityFeatureConfiguration(0.02F)));

	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> UNDERWATER_CAVE_CARVER = register("underwater_cave_carver", LostWorldsWorldCarvers.UNDERWATER_CAVE_CARVER.configured(new ProbabilityFeatureConfiguration(0.14285715F)));
	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> UNDERWATER_CANYON_CARVER = register("underwater_canyon_carver", LostWorldsWorldCarvers.UNDERWATER_CANYON_CARVER.configured(new ProbabilityFeatureConfiguration(0.02F)));

	public static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> register(String id, ConfiguredWorldCarver<WC> configuredCarver) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_CARVER, LostWorldsUtils.rL(id), configuredCarver);
	}

	public static void init() {
	}
}
