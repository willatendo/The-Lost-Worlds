package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.CastleTransformer;

public class CretaceousRiverLayer implements CastleTransformer {
	public CretaceousRiverLayer() {
	}

	@Override
	public int apply(Context random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RIVER);
		}

		return -1;
	}
}
