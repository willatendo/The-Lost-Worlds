package lostworlds.library.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.library.biome.ModConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CalamitesTree extends Tree {
	@Nullable
	@Override
	public ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean big) {
		return ModConfiguredFeatures.CALAMITIES_TREE;
	}
}
