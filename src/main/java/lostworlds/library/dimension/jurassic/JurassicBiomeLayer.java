package lostworlds.library.dimension.jurassic;

import lostworlds.library.biome.BiomeKeys;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class JurassicBiomeLayer implements IC0Transformer {
	private final int[] landIds;

	public JurassicBiomeLayer() {
		this.landIds = new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_MOUNTAINS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST_HILLS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_MARSH), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SWAMP), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_FEN), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_BOG), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ERRODED_MOUNTAINS), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_VOLCANIC_RANGE), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.DEEP_JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.WARM_JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_RIVER) };
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int center) {
		if (JurassicLayerUtil.isLand(center)) {
			return landIds[iNoiseRandom.nextRandom(landIds.length)];
		}

		return center;
	}
}
