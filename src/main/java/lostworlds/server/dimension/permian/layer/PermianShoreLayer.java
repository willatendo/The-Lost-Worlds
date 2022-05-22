package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class PermianShoreLayer implements BishopTransformer {
	public PermianShoreLayer() {
	}

	@Override
	public int apply(Context iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (PermianLayerUtil.isOcean(center) && (!PermianLayerUtil.isOcean(ne) || !PermianLayerUtil.isOcean(se) || !PermianLayerUtil.isOcean(sw) || !PermianLayerUtil.isOcean(nw))) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_SHORE);
		}

		if (PermianLayerUtil.isRiver(center) && (!PermianLayerUtil.isRiver(ne) || !PermianLayerUtil.isRiver(se) || !PermianLayerUtil.isRiver(sw) || !PermianLayerUtil.isRiver(nw))) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_SHORE);
		}

		return center;
	}
}
