package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.CastleTransformer;

public class JurassicRiverLayer implements CastleTransformer {
	public JurassicRiverLayer() {
	}

	@Override
	public int apply(Context random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_RIVER);
		}

		return -1;
	}
}
