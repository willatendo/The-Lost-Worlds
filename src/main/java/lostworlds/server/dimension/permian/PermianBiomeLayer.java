package lostworlds.server.dimension.permian;

import lostworlds.server.biome.BiomeKeys;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public final class PermianBiomeLayer implements C0Transformer {
	private final int[] landIds;

	public PermianBiomeLayer() {
		this.landIds = new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_FLOOD_BASALTS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_ASHY_MEDOWS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_MOUNTAINS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS_HILLS), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_MARSH), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT), PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT_HILLS) };
	}

	@Override
	public int apply(Context iNoiseRandom, int center) {
		if (PermianLayerUtil.isLand(center)) {
			return landIds[iNoiseRandom.nextRandom(landIds.length)];
		}

		return center;
	}
}
