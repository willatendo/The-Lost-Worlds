package lostworlds.library.dimension.jurassic.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class JurassicAddInlandLayer implements IBishopTransformer {
	private final int chance;

	public JurassicAddInlandLayer(int chance) {
		this.chance = chance;
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) {
		if (JurassicLayerUtil.isLand(nw) && JurassicLayerUtil.isLand(sw) && JurassicLayerUtil.isLand(ne) && JurassicLayerUtil.isLand(se) && JurassicLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST);
		}

		return center;
	}
}
