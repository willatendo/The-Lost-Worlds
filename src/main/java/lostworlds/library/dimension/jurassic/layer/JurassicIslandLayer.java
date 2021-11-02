package lostworlds.library.dimension.jurassic.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.JurassicLayerUtil;
import tyrannotitanlib.library.base.dimension.layer.IIslandLayer;

public class JurassicIslandLayer implements IIslandLayer 
{
	@Override
	public int land() 
	{
		return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST);
	}

	@Override
	public int ocean() 
	{
		return JurassicLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
	}
}
