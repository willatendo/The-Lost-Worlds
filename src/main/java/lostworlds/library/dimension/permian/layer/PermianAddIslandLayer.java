package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class PermianAddIslandLayer implements IBishopTransformer {
	private final int chance;
	private final int landId;

	PermianAddIslandLayer(int chance, int landId) {
		this.chance = chance;
		this.landId = landId;
	}

	public static PermianAddIslandLayer desert2() {
		return new PermianAddIslandLayer(2, PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT));
	}

	public static PermianAddIslandLayer desert3() {
		return new PermianAddIslandLayer(3, PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT));
	}

	public static PermianAddIslandLayer desert8() {
		return new PermianAddIslandLayer(8, PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT));
	}

	public static PermianAddIslandLayer mountains() {
		return new PermianAddIslandLayer(13, PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_MOUNTAINS));
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) {
		if (!PermianLayerUtil.isLand(nw) && !PermianLayerUtil.isLand(sw) && !PermianLayerUtil.isLand(ne) && !PermianLayerUtil.isLand(se) && !PermianLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return landId;
		}

		return center;
	}
}
