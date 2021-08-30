package lostworlds.library.dimension.permian.layer.river;

import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public enum PermianStartRiverLayer implements IC0Transformer 
{
	INSTANCE;

	public int apply(INoiseRandom noise, int seed) 
	{
		return PermianLayerUtil.isShallowOcean(seed) ? seed : noise.nextRandom(299999) + 2;
	}
}
