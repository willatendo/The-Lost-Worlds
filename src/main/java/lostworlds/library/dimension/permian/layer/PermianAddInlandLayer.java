package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class PermianAddInlandLayer implements IBishopTransformer {
	private final int chance;

	public PermianAddInlandLayer(int chance) {
		this.chance = chance;
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) {
		if (PermianLayerUtil.isLand(nw) && PermianLayerUtil.isLand(sw) && PermianLayerUtil.isLand(ne) && PermianLayerUtil.isLand(se) && PermianLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
		}

		return center;
	}
}
