package lostworlds.library.dimension.jurassic.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class JurassicRiverLayer implements ICastleTransformer {
	public JurassicRiverLayer() {
	}

	@Override
	public int apply(INoiseRandom random, int north, int east, int south, int west, int center) {
		if (center != north || center != east || center != south || center != west) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_RIVER);
		}

		return -1;
	}
}
