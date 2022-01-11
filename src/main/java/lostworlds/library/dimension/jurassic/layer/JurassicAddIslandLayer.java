package lostworlds.library.dimension.jurassic.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class JurassicAddIslandLayer implements IBishopTransformer {
	private final int chance;
	private final int landId;

	JurassicAddIslandLayer(int chance, int landId) {
		this.chance = chance;
		this.landId = landId;
	}

	public static JurassicAddIslandLayer forest2() {
		return new JurassicAddIslandLayer(2, JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST));
	}

	public static JurassicAddIslandLayer forest3() {
		return new JurassicAddIslandLayer(3, JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST));
	}

	public static JurassicAddIslandLayer forest8() {
		return new JurassicAddIslandLayer(8, JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST));
	}

	public static JurassicAddIslandLayer mountains() {
		return new JurassicAddIslandLayer(13, JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_MOUNTAINS));
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) {
		if (!JurassicLayerUtil.isLand(nw) && !JurassicLayerUtil.isLand(sw) && !JurassicLayerUtil.isLand(ne) && !JurassicLayerUtil.isLand(se) && !JurassicLayerUtil.isLand(center) && random.nextRandom(chance) == 0) {
			return landId;
		}

		return center;
	}
}
