package lostworlds.server.dimension.permian.layer;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public class PermianAddSubBiomeLayer implements C0Transformer {
	final int baseID;
	final int[] subBiomeIDs;

	public PermianAddSubBiomeLayer(final int baseID, final int[] subBiomeIDs) {
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static PermianAddSubBiomeLayer floodBasalts() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_FLOOD_BASALTS), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_FLOOD_BASALTS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_ASHY_MEDOWS) });
	}

	public static PermianAddSubBiomeLayer coniferForest() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS) });
	}

	public static PermianAddSubBiomeLayer ginkgoForest() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS) });
	}

	public static PermianAddSubBiomeLayer desert() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT_HILLS) });
	}

	public static PermianAddSubBiomeLayer driedPlains() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS) });
	}

	public static PermianAddSubBiomeLayer plains() {
		return new PermianAddSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS_HILLS) });
	}

	@Override
	public int apply(Context random, int center) {
		if (center == baseID) {
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} else {
			return center;
		}
	}
}
