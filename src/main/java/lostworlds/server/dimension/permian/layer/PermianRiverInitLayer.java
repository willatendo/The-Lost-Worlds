package lostworlds.server.dimension.permian.layer;

import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class PermianRiverInitLayer implements IC0Transformer {
	public PermianRiverInitLayer() {
	}

	@Override
	public int apply(INoiseRandom random, int centre) {
		return PermianLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
