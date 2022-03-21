package lostworlds.server.feature;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.placement.Height55To60;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class LostWorldsPlacements {
	public static final Placement<NoPlacementConfig> NEST = LostWorldsRegistry.register("nest", new Height55To60(NoPlacementConfig.CODEC));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Placement");
	}
}
