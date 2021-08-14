package lostworlds.library.tree;

import java.util.Random;

import lostworlds.library.biome.ModConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class GinkgoTree extends Tree
{

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean big) 
	{
		return ModConfiguredFeatures.GINKGO_TREE;
	}

}
