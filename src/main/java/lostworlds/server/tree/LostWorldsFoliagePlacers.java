package lostworlds.server.tree;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class LostWorldsFoliagePlacers {
	public static final FoliagePlacerType<?> CONIFER_FOLIAGE_PLACER = LostWorldsRegistry.register("conifer_foliage_placer", new FoliagePlacerType(ConiferFoliagePlacer.CODEC));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Foliage Placer Types");
	}
}
