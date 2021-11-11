package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import tyrannotitanlib.library.base.dimension.layer.IIslandLayer;

public class CretaceousIslandLayer implements IIslandLayer 
{
	@Override
	public int land() 
	{
		return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST);
	}

	@Override
	public int ocean() 
	{
		return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN);
	}
}
