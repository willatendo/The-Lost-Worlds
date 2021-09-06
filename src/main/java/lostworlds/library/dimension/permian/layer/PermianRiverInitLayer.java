package lostworlds.library.dimension.permian.layer;

import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class PermianRiverInitLayer implements IC0Transformer 
{
	public PermianRiverInitLayer() { }

	@Override
	public int apply(INoiseRandom random, int center) 
	{
		return PermianLayerUtil.isOcean(center) ? center : random.nextRandom(4) + 1;
	}
}
