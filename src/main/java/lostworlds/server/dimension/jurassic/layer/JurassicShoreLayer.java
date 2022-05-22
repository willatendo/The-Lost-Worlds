package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class JurassicShoreLayer implements BishopTransformer {
	public JurassicShoreLayer() {
	}

	@Override
	public int apply(Context iNoiseRandom, int ne, int se, int sw, int nw, int center) {
		if (JurassicLayerUtil.isOcean(center) && (!JurassicLayerUtil.isOcean(ne) || !JurassicLayerUtil.isOcean(se) || !JurassicLayerUtil.isOcean(sw) || !JurassicLayerUtil.isOcean(nw))) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SHORE);
		}

		if (JurassicLayerUtil.isRiver(center) && (!JurassicLayerUtil.isRiver(ne) || !JurassicLayerUtil.isRiver(se) || !JurassicLayerUtil.isRiver(sw) || !JurassicLayerUtil.isRiver(nw))) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SHORE);
		}

		return center;
	}
}
