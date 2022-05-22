package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer2;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

public class JurassicRiverMixLayer implements AreaTransformer2, DimensionOffset0Transformer {
	public JurassicRiverMixLayer() {
	}

	@Override
	public int applyPixel(Context random, Area parent1, Area parent2, int x, int y) {
		final int biome = parent1.get(getParentX(x), getParentY(y));
		final int river = parent2.get(getParentX(x), getParentY(y));

		if (!JurassicLayerUtil.isOcean(biome)) {
			if (JurassicLayerUtil.isRiver(river)) {
				return river;
			}
		}
		return biome;
	}
}
