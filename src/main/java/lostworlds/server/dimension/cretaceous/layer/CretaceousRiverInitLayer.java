package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public final class CretaceousRiverInitLayer implements C0Transformer {
	public CretaceousRiverInitLayer() {
	}

	@Override
	public int apply(Context random, int centre) {
		return CretaceousLayerUtil.isOcean(centre) ? centre : random.nextRandom(4) + 1;
	}
}
