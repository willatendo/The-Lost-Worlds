package lostworlds.library.block.tree;

import java.util.Random;

import lostworlds.library.biome.ModConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CypressTree extends Tree
{
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean big) 
	{
		return ModConfiguredFeatures.CYPRESS_TREE;
	}
}
