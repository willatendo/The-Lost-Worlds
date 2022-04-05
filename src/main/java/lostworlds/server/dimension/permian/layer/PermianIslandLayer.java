package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.IIslandLayer;
import lostworlds.server.dimension.permian.PermianLayerUtil;

public class PermianIslandLayer implements IIslandLayer {
	@Override
	public int land() {
		return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
	}

	@Override
	public int ocean() {
		return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
	}
}
