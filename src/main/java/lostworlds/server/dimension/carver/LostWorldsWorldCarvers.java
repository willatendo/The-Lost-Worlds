package lostworlds.server.dimension.carver;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsWorldCarvers {
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, LostWorldsUtils.ID);

	public static final WorldCarver<ProbabilityConfig> CAVE_CARVER = register("cave_carver", new ModCaveCarver(ProbabilityConfig.CODEC, 256));
	public static final WorldCarver<ProbabilityConfig> CANYON_CARVER = register("canyon_carver", new ModCayonCarver(ProbabilityConfig.CODEC));

	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CAVE_CARVER = register("underwater_cave_carver", new ModUnderwaterCaveCarver(ProbabilityConfig.CODEC));
	public static final WorldCarver<ProbabilityConfig> UNDERWATER_CANYON_CARVER = register("underwater_canyon_carver", new ModUnderwaterCayonCarver(ProbabilityConfig.CODEC));

	public static WorldCarver<ProbabilityConfig> register(String id, WorldCarver<ProbabilityConfig> worldCarver) {
		WORLD_CARVERS.register(id, () -> worldCarver);
		return worldCarver;
	}

	public static void deferred(IEventBus bus) {
		WORLD_CARVERS.register(bus);
	}
}
