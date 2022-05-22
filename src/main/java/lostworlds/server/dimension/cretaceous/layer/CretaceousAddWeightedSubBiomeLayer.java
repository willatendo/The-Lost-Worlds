package lostworlds.server.dimension.cretaceous.layer;

import java.util.List;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.util.WeighedRandom;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.C0Transformer;

public class CretaceousAddWeightedSubBiomeLayer implements C0Transformer {
	private List<WeighedRandom.WeighedRandomItem> biomeWeights;
	private int totalWeight;
	final int baseID;
	final int[] subBiomeIDs;
	private final Object2IntMap<WeighedRandom.WeighedRandomItem> biomeLookup = new Object2IntOpenHashMap<>();

	public CretaceousAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeighedRandom.WeighedRandomItem... weights) {
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

	public static CretaceousAddWeightedSubBiomeLayer ocean() {
		return new CretaceousAddWeightedSubBiomeLayer(CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN), new int[] { CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_CRETACEOUS_OCEAN), CretaceousLayerUtil.getBiomeId(BiomeKeys.COLD_DEEP_CRETACEOUS_OCEAN) }, new WeighedRandom.WeighedRandomItem(20), new WeighedRandom.WeighedRandomItem(4), new WeighedRandom.WeighedRandomItem(20), new WeighedRandom.WeighedRandomItem(4), new WeighedRandom.WeighedRandomItem(20), new WeighedRandom.WeighedRandomItem(4));
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
