package lostworlds.server.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.biome.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SequoiaTree extends AbstractMegaTreeGrower {
	@Nullable
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.BABY_SEQUOIA_TREE;
	}

	@Nullable
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredMegaFeature(Random rand) {
		return TreeFeatures.SEQUOIA_TREE;
	}
}
