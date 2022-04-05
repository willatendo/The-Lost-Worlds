package lostworlds.server.placement;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPlacements {
	public static final Placement<NoPlacementConfig> NEST = register("nest", new Height55To60(NoPlacementConfig.CODEC));

	public static <T extends IPlacementConfig> Placement<T> register(String id, Placement<T> placement) {
		placement.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.DECORATORS.register(placement);
		return placement;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Placement");
	}
}
