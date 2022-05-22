package lostworlds.server.block.tree;

import java.util.Random;

import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.world.level.levelgen.feature.Feature;

public class GinkgoTree extends CustomTree {
	@Override
	protected Feature getFeature(Random random) {
		return LostWorldsFeatures.GINKGO_TREE;
	}
}
