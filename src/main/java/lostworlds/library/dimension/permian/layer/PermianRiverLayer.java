package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class PermianRiverLayer implements ICastleTransformer {
	public PermianRiverLayer() {
	}

	@Override
	public int apply(INoiseRandom random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER);
		}

		return -1;
	}
}
