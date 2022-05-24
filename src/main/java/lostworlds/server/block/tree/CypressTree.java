package lostworlds.server.block.tree;

import java.util.Random;

import lostworlds.server.biome.features.configured.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CypressTree extends AbstractTreeGrower {
	@Override
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.CYPRESS_TREE;
	}
}
