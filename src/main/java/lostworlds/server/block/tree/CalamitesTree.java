package lostworlds.server.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.biome.features.configured.TreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CalamitesTree extends AbstractTreeGrower {
	@Nullable
	@Override
	public Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random rand, boolean big) {
		return TreeFeatures.CALAMITIES_TREE;
	}
}
