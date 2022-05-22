package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public final class JurassicRiverInitLayer implements C0Transformer {
	public JurassicRiverInitLayer() {
	}

	@Override
	public int apply(Context random, int centre) {
		return JurassicLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
