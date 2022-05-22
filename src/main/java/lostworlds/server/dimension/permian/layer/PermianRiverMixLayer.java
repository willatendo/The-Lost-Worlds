package lostworlds.server.dimension.permian.layer;

import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer2;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

public class PermianRiverMixLayer implements AreaTransformer2, DimensionOffset0Transformer {
	public PermianRiverMixLayer() {
	}

	@Override
	public int applyPixel(Context random, Area parent1, Area parent2, int x, int y) {
		final int biome = parent1.get(getParentX(x), getParentY(y));
		final int river = parent2.get(getParentX(x), getParentY(y));

		if (!PermianLayerUtil.isOcean(biome)) {
			if (PermianLayerUtil.isRiver(river)) {
				return river;
			}
		}
		return biome;
	}
}
