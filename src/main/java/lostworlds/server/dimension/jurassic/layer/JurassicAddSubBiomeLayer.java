package lostworlds.server.dimension.jurassic.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class JurassicAddSubBiomeLayer implements IC0Transformer {
	private final int baseID;
	private final int[] subBiomeIDs;

	public JurassicAddSubBiomeLayer(final int baseID, final int[] subBiomeIDs) {
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static JurassicAddSubBiomeLayer araucariaForest() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST_HILLS) });
	}

	public static JurassicAddSubBiomeLayer coniferForest() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST_HILLS) });
	}

	public static JurassicAddSubBiomeLayer ginkgoForest() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST_HILLS) });
	}

	public static JurassicAddSubBiomeLayer redwoodsForest() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST_HILLS) });
	}

	public static JurassicAddSubBiomeLayer desert() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT_HILLS) });
	}

	public static JurassicAddSubBiomeLayer plains() {
		return new JurassicAddSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS_HILLS) });
	}

	@Override
	public int apply(INoiseRandom random, int center) {
		if (center == baseID) {
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} else {
			return center;
		}
	}
}
