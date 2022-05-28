package lostworlds.server.block.tree;

import java.util.Random;

import lostworlds.server.biome.features.configured.TreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class GinkgoTree extends AbstractTreeGrower {
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.GINKGO_TREE;
	}
}
