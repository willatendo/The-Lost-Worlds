package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class JurassicAddInlandLayer implements BishopTransformer {
	private final int chance;

	public JurassicAddInlandLayer(int chance) {
		this.chance = chance;
	}

	@Override
	public int apply(Context random, int ne, int se, int sw, int nw, int center) {
		if (JurassicLayerUtil.isLand(nw) && JurassicLayerUtil.isLand(sw) && JurassicLayerUtil.isLand(ne) && JurassicLayerUtil.isLand(se) && JurassicLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST);
		}

		return center;
	}
}
