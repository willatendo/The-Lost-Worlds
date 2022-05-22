package lostworlds.server.dimension.permian.layer;

import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public final class PermianRiverInitLayer implements C0Transformer {
	public PermianRiverInitLayer() {
	}

	@Override
	public int apply(Context random, int centre) {
		return PermianLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
