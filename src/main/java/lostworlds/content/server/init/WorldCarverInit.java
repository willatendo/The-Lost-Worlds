package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.dimension.permian.carver.PermianCaveCarver;
import lostworlds.library.dimension.permian.carver.PermianCayonCarver;
import lostworlds.library.dimension.permian.carver.PermianUnderwaterCaveCarver;
import lostworlds.library.dimension.permian.carver.PermianUnderwaterCayonCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class WorldCarverInit 
{
	public static final WorldCarver<ProbabilityConfig> PERMIAN_CAVE_CARVER = ModRegistry.register("permian_cave_carver", new PermianCaveCarver(ProbabilityConfig.CODEC, 256));
	public static final WorldCarver<ProbabilityConfig> PERMIAN_CANYON_CARVER = ModRegistry.register("permian_canyon_carver", new PermianCayonCarver(ProbabilityConfig.CODEC));
	
	public static final WorldCarver<ProbabilityConfig> PERMIAN_UNDERWATER_CAVE_CARVER = ModRegistry.register("permian_underwater_cave_carver", new PermianUnderwaterCaveCarver(ProbabilityConfig.CODEC));
	public static final WorldCarver<ProbabilityConfig> PERMIAN_UNDERWATER_CANYON_CARVER = ModRegistry.register("permian_underwater_canyon_carver", new PermianUnderwaterCayonCarver(ProbabilityConfig.CODEC));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod World Carvers"); }
}
