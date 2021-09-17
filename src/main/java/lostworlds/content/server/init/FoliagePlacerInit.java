package lostworlds.content.server.init;

import lostworlds.library.foliageplacer.ConiferFoliagePlacer;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class FoliagePlacerInit 
{
	public static final FoliagePlacerType<?> CONIFER_FOLIAGE_PLACER = ModRegistry.register("conifer_foliage_placer", new FoliagePlacerType(ConiferFoliagePlacer.CODEC));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Foliage Placer Types"); }
}
