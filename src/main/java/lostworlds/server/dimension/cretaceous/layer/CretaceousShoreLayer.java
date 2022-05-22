package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class CretaceousShoreLayer implements BishopTransformer {
	public CretaceousShoreLayer() {
	}

	@Override
	public int apply(Context iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (CretaceousLayerUtil.isOcean(center) && (!CretaceousLayerUtil.isOcean(ne) || !CretaceousLayerUtil.isOcean(se) || !CretaceousLayerUtil.isOcean(sw) || !CretaceousLayerUtil.isOcean(nw))) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE);
		}

		if (CretaceousLayerUtil.isRiver(center) && (!CretaceousLayerUtil.isRiver(ne) || !CretaceousLayerUtil.isRiver(se) || !CretaceousLayerUtil.isRiver(sw) || !CretaceousLayerUtil.isRiver(nw))) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE);
		}

		return center;
	}
}
