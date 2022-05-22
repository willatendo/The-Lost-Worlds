package lostworlds.server.dimension.carver;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsWorldCarvers {
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, LostWorldsUtils.ID);

	public static final WorldCarver<ProbabilityFeatureConfiguration> CAVE_CARVER = register("cave_carver", new ModCaveCarver(ProbabilityFeatureConfiguration.CODEC, 256));
	public static final WorldCarver<ProbabilityFeatureConfiguration> CANYON_CARVER = register("canyon_carver", new ModCayonCarver(ProbabilityFeatureConfiguration.CODEC));

	public static final WorldCarver<ProbabilityFeatureConfiguration> UNDERWATER_CAVE_CARVER = register("underwater_cave_carver", new ModUnderwaterCaveCarver(ProbabilityFeatureConfiguration.CODEC));
	public static final WorldCarver<ProbabilityFeatureConfiguration> UNDERWATER_CANYON_CARVER = register("underwater_canyon_carver", new ModUnderwaterCayonCarver(ProbabilityFeatureConfiguration.CODEC));

	public static WorldCarver<ProbabilityFeatureConfiguration> register(String id, WorldCarver<ProbabilityFeatureConfiguration> worldCarver) {
		WORLD_CARVERS.register(id, () -> worldCarver);
		return worldCarver;
	}

	public static void deferred(IEventBus bus) {
		WORLD_CARVERS.register(bus);
	}
}
