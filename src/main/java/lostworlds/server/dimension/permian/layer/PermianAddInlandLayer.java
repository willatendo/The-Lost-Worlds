package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class PermianAddInlandLayer implements BishopTransformer {
	private final int chance;

	public PermianAddInlandLayer(int chance) {
		this.chance = chance;
	}

	@Override
	public int apply(Context random, int ne, int se, int sw, int nw, int center) {
		if (PermianLayerUtil.isLand(nw) && PermianLayerUtil.isLand(sw) && PermianLayerUtil.isLand(ne) && PermianLayerUtil.isLand(se) && PermianLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
		}

		return center;
	}
}
