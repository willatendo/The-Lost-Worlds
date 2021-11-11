package lostworlds.library.dimension.cretaceous;

import lostworlds.library.biome.BiomeKeys;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class CretaceousBiomeLayer implements IC0Transformer   
{
    private final int[] landIds;

	public CretaceousBiomeLayer() 
	{
        this.landIds = new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC_SPIRES), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_BOG), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ERRODED_MOUNTAINS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FEN), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FLOOD_BASALTS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GAME_TRAIL), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MARSH), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MEDOW), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MOUNTAINS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT_HILLS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RIVER), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SWAMP), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_CRETACEOUS_OCEAN) };
    }

	@Override
	public int apply(INoiseRandom iNoiseRandom, int center) 
	{
		if(CretaceousLayerUtil.isLand(center)) 
		{
			return landIds[iNoiseRandom.nextRandom(landIds.length)];
		}

		return center;
	}
}
