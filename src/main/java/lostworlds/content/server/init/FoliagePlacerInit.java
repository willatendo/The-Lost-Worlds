package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.tree.ConiferFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class FoliagePlacerInit 
{
	public static final FoliagePlacerType<?> CONIFER_FOLIAGE_PLACER = ModRegistry.register("conifer_foliage_placer", new FoliagePlacerType(ConiferFoliagePlacer.CODEC));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Foliage Placer Types"); }
}
