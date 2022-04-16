package lostworlds.server.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.biome.TreeFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AraucariaTree extends Tree {
	@Nullable
	@Override
	public ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.ARAUCARIA_TREE.get();
	}
}
