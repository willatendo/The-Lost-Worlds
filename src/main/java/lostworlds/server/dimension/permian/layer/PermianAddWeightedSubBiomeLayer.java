package lostworlds.server.dimension.permian.layer;

import java.util.List;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.PermianLayerUtil;
import net.minecraft.util.WeighedRandom;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public class PermianAddWeightedSubBiomeLayer implements C0Transformer {
	private List<WeighedRandom.WeighedRandomItem> biomeWeights;
	private int totalWeight;
	final int baseID;
	final int[] subBiomeIDs;
	private final Object2IntMap<WeighedRandom.WeighedRandomItem> biomeLookup = new Object2IntOpenHashMap<>();

	public PermianAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeighedRandom.WeighedRandomItem... weights) {
		if (weights.length > 0) {
			biomeWeights = Lists.newArrayList(weights);
			totalWeight = WeighedRandom.getTotalWeight(biomeWeights);
			for (int i = 0; i < weights.length; i++) {
				biomeLookup.put(weights[i], subBiomeIDs[i]);
			}
		}
		this.baseID = baseID;
		this.subBiomeIDs = subBiomeIDs;
	}

	public static PermianAddWeightedSubBiomeLayer ocean() {
		return new PermianAddWeightedSubBiomeLayer(PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN), new int[] { PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN), PermianLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN) }, new WeighedRandom.WeighedRandomItem(20), new WeighedRandom.WeighedRandomItem(4), new WeighedRandom.WeighedRandomItem(20), new WeighedRandom.WeighedRandomItem(4));
	}

	@Override
	public int apply(Context random, int center) {
		if (center == baseID) {
			if (biomeLookup.size() > 0) {
				return biomeLookup.getInt(WeighedRandom.getWeightedItem(biomeWeights, random.nextRandom(totalWeight)));
			}
			return subBiomeIDs[random.nextRandom(subBiomeIDs.length)];
		} else {
			return center;
		}
	}
}
