package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.placement.Height55To70;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class PlacementInit {
	public static final Placement<NoPlacementConfig> NEST = ModRegistry.register("nest", new Height55To70(NoPlacementConfig.CODEC));

	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Placement");
	}
}
