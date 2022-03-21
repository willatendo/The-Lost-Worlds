package lostworlds.server.dimension.permian.layer;

import java.util.List;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class PermianAddWeightedSubBiomeLayer implements IC0Transformer {
	private List<WeightedRandom.Item> biomeWeights;
	private int totalWeight;
	final int baseID;
	final int[] subBiomeIDs;
	private final Object2IntMap<WeightedRandom.Item> biomeLookup = new Object2IntOpenHashMap<>();

	public PermianAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeightedRandom.Item... weights) {
		if (weights.length > 0) {
			biomeWeights = Lists.newArrayList(weights);
			totalWeight = WeightedRandom.getTotalWeight(biomeWeights);
			for (int i = 0; i < weights.length; i++) {
				biomeLookup.put(weights[i], subBiomeIDs[i]);
			}
		}
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static PermianAddWeightedSubBiomeLayer ocean() {
		return new PermianAddWeightedSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN) }, new WeightedRandom.Item(20), new WeightedRandom.Item(4), new WeightedRandom.Item(20), new WeightedRandom.Item(4));
	}

	@Override
	public int apply(INoiseRandom random, int center) {
		if (center == baseID) {
			if (biomeLookup.size() > 0) {
				return biomeLookup.getInt(WeightedRandom.getWeightedItem(biomeWeights, random.nextRandom(totalWeight)));
			}
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} else {
			return center;
		}
	}
}
