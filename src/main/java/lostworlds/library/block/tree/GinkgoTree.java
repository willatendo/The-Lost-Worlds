package lostworlds.library.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.library.biome.ModConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class GinkgoTree extends BigTree
{
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean big) 
	{
		return ModConfiguredFeatures.GINKGO_TREE;
	}

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random rand) 
	{
		return ModConfiguredFeatures.MASSIVE_GINKGO;
	}
}
