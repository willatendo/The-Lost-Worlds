package lostworlds.library.dimension.jurassic.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class JurassicShoreLayer implements IBishopTransformer {
	public JurassicShoreLayer() {
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (JurassicLayerUtil.isOcean(center) && (!JurassicLayerUtil.isOcean(ne) || !JurassicLayerUtil.isOcean(se) || !JurassicLayerUtil.isOcean(sw) || !JurassicLayerUtil.isOcean(nw))) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SHORE);
		}

		if (JurassicLayerUtil.isRiver(center) && (!JurassicLayerUtil.isRiver(ne) || !JurassicLayerUtil.isRiver(se) || !JurassicLayerUtil.isRiver(sw) || !JurassicLayerUtil.isRiver(nw))) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SHORE);
		}

		return center;
	}
}
