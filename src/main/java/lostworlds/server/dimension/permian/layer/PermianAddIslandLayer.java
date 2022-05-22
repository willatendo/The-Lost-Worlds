package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.BishopTransformer;

public class PermianAddIslandLayer implements BishopTransformer {
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
	public int apply(Context random, int ne, int se, int sw, int nw, int center) {
		if (!PermianLayerUtil.isLand(nw) && !PermianLayerUtil.isLand(sw) && !PermianLayerUtil.isLand(ne) && !PermianLayerUtil.isLand(se) && !PermianLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return landId;
		}

		return center;
	}
}
