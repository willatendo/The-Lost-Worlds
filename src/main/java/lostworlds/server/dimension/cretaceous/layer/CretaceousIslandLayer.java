package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.IIslandLayer;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;

public class CretaceousIslandLayer implements IIslandLayer {
	@Override
	public int land() {
		return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST);
	}

	@Override
	public int ocean() {
		return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN);
	}
}
