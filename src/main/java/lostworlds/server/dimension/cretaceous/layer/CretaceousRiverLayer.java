package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class CretaceousRiverLayer implements ICastleTransformer {
	public CretaceousRiverLayer() {
	}

	@Override
	public int apply(INoiseRandom random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RIVER);
		}

		return -1;
	}
}
