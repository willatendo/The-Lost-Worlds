package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class CretaceousRiverInitLayer implements IC0Transformer 
{
	public CretaceousRiverInitLayer() { }
	
	@Override
	public int apply(INoiseRandom random, int centre) 
	{
		return CretaceousLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
