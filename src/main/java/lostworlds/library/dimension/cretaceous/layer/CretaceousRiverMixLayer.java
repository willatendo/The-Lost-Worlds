package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public class CretaceousRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer 
{
	public CretaceousRiverMixLayer() { }

	@Override
	public int applyPixel(INoiseRandom random, IArea parent1, IArea parent2, int x, int y) 
	{
		final int biome = parent1.get(getParentX(x), getParentY(y));
		final int river = parent2.get(getParentX(x), getParentY(y));

		if(!CretaceousLayerUtil.isOcean(biome)) 
		{
			if(CretaceousLayerUtil.isRiver(river)) 
			{
				return river;
			}
		}
		return biome;
	}
}
