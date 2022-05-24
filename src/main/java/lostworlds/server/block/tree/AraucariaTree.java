package lostworlds.server.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.biome.features.configured.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class AraucariaTree extends AbstractTreeGrower {
	@Nullable
	@Override
	public ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.ARAUCARIA_TREE;
	}
}
