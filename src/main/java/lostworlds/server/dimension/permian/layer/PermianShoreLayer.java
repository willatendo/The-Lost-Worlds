package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class PermianShoreLayer implements IBishopTransformer {
	public PermianShoreLayer() {
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (PermianLayerUtil.isOcean(center) && (!PermianLayerUtil.isOcean(ne) || !PermianLayerUtil.isOcean(se) || !PermianLayerUtil.isOcean(sw) || !PermianLayerUtil.isOcean(nw))) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_SHORE);
		}

		if (PermianLayerUtil.isRiver(center) && (!PermianLayerUtil.isRiver(ne) || !PermianLayerUtil.isRiver(se) || !PermianLayerUtil.isRiver(sw) || !PermianLayerUtil.isRiver(nw))) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_SHORE);
		}

		return center;
	}
}
