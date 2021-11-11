package lostworlds.library.dimension.cretaceous.layer;

import java.util.List;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class CretaceousAddWeightedSubBiomeLayer implements IC0Transformer 
{
	private List<WeightedRandom.Item> biomeWeights;
	private int totalWeight;
	final int baseID;
	final int[] subBiomeIDs;
	private final Object2IntMap<WeightedRandom.Item> biomeLookup = new Object2IntOpenHashMap<>();

	public CretaceousAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeightedRandom.Item... weights) 
	{
		if(weights.length > 0) 
		{
			biomeWeights = Lists.newArrayList(weights);
			totalWeight = WeightedRandom.getTotalWeight(biomeWeights);
			for(int i = 0; i < weights.length; i++) 
			{
				biomeLookup.put(weights[i], subBiomeIDs[i]);
			}
		}
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static CretaceousAddWeightedSubBiomeLayer ocean() 
	{
		return new CretaceousAddWeightedSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_DEEP_CRETACEOUS_OCEAN) }, new WeightedRandom.Item(20), new WeightedRandom.Item(4), new WeightedRandom.Item(20), new WeightedRandom.Item(4), new WeightedRandom.Item(20), new WeightedRandom.Item(4));
	}

	@Override
	public int apply(INoiseRandom random, int center) 
	{
		if(center == baseID) 
		{
			if(biomeLookup.size() > 0) 
			{
				return biomeLookup.getInt(WeightedRandom.getWeightedItem(biomeWeights, random.nextRandom(totalWeight)));
			}
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} 
		else 
		{
			return center;
		}
	}
}
