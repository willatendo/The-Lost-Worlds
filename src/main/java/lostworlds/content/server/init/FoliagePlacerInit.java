package lostworlds.content.server.init;

import lostworlds.library.foliageplacer.ConiferFoliagePlacer;
import lostworlds.library.util.ModRegistry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class FoliagePlacerInit 
{
	public static final FoliagePlacerType<?> CONIFER_FOLIAGE_PLACER = ModRegistry.register("conifer_foliage_placer", new FoliagePlacerType(ConiferFoliagePlacer.CODEC));
	
	public static void init() { }
}
