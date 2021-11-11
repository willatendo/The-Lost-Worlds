package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class CretaceousAddSubBiomeLayer implements IC0Transformer 
{
	final int baseID;
	final int[] subBiomeIDs;

	public CretaceousAddSubBiomeLayer(final int baseID, final int[] subBiomeIDs) 
	{
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static CretaceousAddSubBiomeLayer araucariaForest() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer coniferForest() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer ginkgoForest() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer frozenForest() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer desert() 
	{	
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer redDesert() 
	{	
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer plains() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS_HILLS) });
	}

	public static CretaceousAddSubBiomeLayer arctic() 
	{
		return new CretaceousAddSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC), CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC_HILLS) });
	}

	@Override
	public int apply(INoiseRandom random, int center) 
	{
		if(center == baseID) 
		{
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} 
		else 
		{
			return center;
		}
	}
}
