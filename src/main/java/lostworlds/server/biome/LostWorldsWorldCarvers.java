package lostworlds.server.biome;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.carver.ModCaveCarver;
import lostworlds.server.dimension.carver.ModCayonCarver;
import lostworlds.server.dimension.carver.ModUnderwaterCaveCarver;
import lostworlds.server.dimension.carver.ModUnderwaterCayonCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class LostWorldsWorldCarvers {
	public static final WorldCarver<ProbabilityConfig> CAVE_CARVER = LostWorldsRegistry.register("cave_carver", new ModCaveCarver(ProbabilityConfig.CODEC, 256));
	public static final WorldCarver<ProbabilityConfig> CANYON_CARVER = LostWorldsRegistry.register("canyon_carver", new ModCayonCarver(ProbabilityConfig.CODEC));

	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = LostWorldsRegistry.register("underwater_cave_carver", new ModUnderwaterCaveCarver(ProbabilityConfig.CODEC));
	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = LostWorldsRegistry.register("underwater_canyon_carver", new ModUnderwaterCayonCarver(ProbabilityConfig.CODEC));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod World Carvers");
	}
}
