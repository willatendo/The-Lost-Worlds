package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer2;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

public class CretaceousRiverMixLayer implements AreaTransformer2, DimensionOffset0Transformer {
	public CretaceousRiverMixLayer() {
	}

	@Override
	public int applyPixel(Context random, Area parent1, Area parent2, int x, int y) {
		final int biome = parent1.get(getParentX(x), getParentY(y));
		final int river = parent2.get(getParentX(x), getParentY(y));

		if (!CretaceousLayerUtil.isOcean(biome)) {
			if (CretaceousLayerUtil.isRiver(river)) {
				return river;
			}
		}
		return biome;
	}
}
