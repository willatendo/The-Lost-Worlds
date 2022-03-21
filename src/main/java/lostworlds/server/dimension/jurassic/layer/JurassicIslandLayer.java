package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import tyrannotitanlib.library.base.dimension.layer.IIslandLayer;

public class JurassicIslandLayer implements IIslandLayer {
	@Override
	public int land() {
		return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST);
	}

	@Override
	public int ocean() {
		return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_OCEAN);
	}
}
