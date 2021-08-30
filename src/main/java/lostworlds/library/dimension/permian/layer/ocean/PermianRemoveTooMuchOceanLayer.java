package lostworlds.library.dimension.permian.layer.ocean;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum PermianRemoveTooMuchOceanLayer implements ICastleTransformer 
{
	INSTANCE;

	public int apply(INoiseRandom noise, int up, int left, int down, int right, int mid) 
	{
		return PermianLayerUtil.isShallowOcean(mid) && PermianLayerUtil.isShallowOcean(up) && PermianLayerUtil.isShallowOcean(left) && PermianLayerUtil.isShallowOcean(right) && PermianLayerUtil.isShallowOcean(down) && noise.nextRandom(2) == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN) ? PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT) : mid;
	}
}
