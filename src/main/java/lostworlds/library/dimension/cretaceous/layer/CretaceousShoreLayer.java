package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class CretaceousShoreLayer implements IBishopTransformer {
	public CretaceousShoreLayer() {
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (CretaceousLayerUtil.isOcean(center) && (!CretaceousLayerUtil.isOcean(ne) || !CretaceousLayerUtil.isOcean(se) || !CretaceousLayerUtil.isOcean(sw) || !CretaceousLayerUtil.isOcean(nw))) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE);
		}

		if (CretaceousLayerUtil.isRiver(center) && (!CretaceousLayerUtil.isRiver(ne) || !CretaceousLayerUtil.isRiver(se) || !CretaceousLayerUtil.isRiver(sw) || !CretaceousLayerUtil.isRiver(nw))) {
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE);
		}

		return center;
	}
}
