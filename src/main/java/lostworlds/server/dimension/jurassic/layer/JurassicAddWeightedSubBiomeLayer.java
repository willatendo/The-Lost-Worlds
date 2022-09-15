package lostworlds.server.dimension.jurassic.layer;

import java.util.List;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.JurassicLayerUtil;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class JurassicAddWeightedSubBiomeLayer implements IC0Transformer {
	private List<WeightedRandom.Item> biomeWeights;
	private int totalWeight;
	private final int baseID;
	private final int[] subBiomeIDs;
	private final Object2IntMap<WeightedRandom.Item> biomeLookup = new Object2IntOpenHashMap<>();

	public JurassicAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeightedRandom.Item... weights) {
		if (weights.length > 0) {
			this.biomeWeights = Lists.newArrayList(weights);
			this.totalWeight = WeightedRandom.getTotalWeight(biomeWeights);
			for (int i = 0; i < weights.length; i++) {
				this.biomeLookup.put(weights[i], subBiomeIDs[i]);
			}
		}
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static JurassicAddWeightedSubBiomeLayer ocean() {
		return new JurassicAddWeightedSubBiomeLayer(JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_OCEAN), new int[] { JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.DEEP_JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.WARM_JURASSIC_OCEAN), JurassicLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_JURASSIC_OCEAN) }, new WeightedRandom.Item(20), new WeightedRandom.Item(4), new WeightedRandom.Item(20), new WeightedRandom.Item(4));
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
