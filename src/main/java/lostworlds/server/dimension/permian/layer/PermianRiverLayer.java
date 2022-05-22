package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.CastleTransformer;

public class PermianRiverLayer implements CastleTransformer {
	public PermianRiverLayer() {
	}

	@Override
	public int apply(Context random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER);
		}

		return -1;
	}
}
