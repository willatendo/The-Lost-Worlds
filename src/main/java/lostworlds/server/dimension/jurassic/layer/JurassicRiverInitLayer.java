package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class JurassicRiverInitLayer implements IC0Transformer {
	public JurassicRiverInitLayer() {
	}

	@Override
	public int apply(INoiseRandom random, int centre) {
		return JurassicLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
