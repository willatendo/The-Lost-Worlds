package lostworlds.server.dimension.cretaceous.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class CretaceousAddIslandLayer implements IBishopTransformer {
	private final int chance;
	private final int landId;

	CretaceousAddIslandLayer(int chance, int landId) {
		this.chance = chance;
		this.landId = landId;
	}

	public static CretaceousAddIslandLayer forest2() {
		return new CretaceousAddIslandLayer(2, CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST));
	}

	public static CretaceousAddIslandLayer forest3() {
		return new CretaceousAddIslandLayer(3, CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST));
	}

	public static CretaceousAddIslandLayer forest8() {
		return new CretaceousAddIslandLayer(8, CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST));
	}

	public static CretaceousAddIslandLayer mountains() {
		return new CretaceousAddIslandLayer(13, CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MOUNTAINS));
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) {
		if (!CretaceousLayerUtil.isLand(nw) && !CretaceousLayerUtil.isLand(sw) && !CretaceousLayerUtil.isLand(ne) && !CretaceousLayerUtil.isLand(se) && !CretaceousLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return landId;
		}

		return center;
	}
}
