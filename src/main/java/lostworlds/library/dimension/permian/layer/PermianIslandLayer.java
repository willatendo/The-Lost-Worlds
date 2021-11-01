package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import tyrannotitanlib.library.base.dimension.layer.IIslandLayer;

public class PermianIslandLayer implements IIslandLayer 
{
	@Override
	public int land() 
	{
		return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
	}

	@Override
	public int ocean() 
	{
		return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
	}
}
