package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import tyrannotitanlib.library.base.dimension.layer.IIslandLayer;

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
