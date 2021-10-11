package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.dimension.carver.ModCaveCarver;
import lostworlds.library.dimension.carver.ModCayonCarver;
import lostworlds.library.dimension.carver.ModUnderwaterCaveCarver;
import lostworlds.library.dimension.carver.ModUnderwaterCayonCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class WorldCarverInit 
{
	public static final WorldCarver<ProbabilityConfig> CAVE_CARVER = ModRegistry.register("cave_carver", new ModCaveCarver(ProbabilityConfig.CODEC, 256));
	public static final WorldCarver<ProbabilityConfig> CANYON_CARVER = ModRegistry.register("canyon_carver", new ModCayonCarver(ProbabilityConfig.CODEC));
	
	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = ModRegistry.register("underwater_cave_carver", new ModUnderwaterCaveCarver(ProbabilityConfig.CODEC));
	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = ModRegistry.register("underwater_canyon_carver", new ModUnderwaterCayonCarver(ProbabilityConfig.CODEC));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod World Carvers"); }
}
