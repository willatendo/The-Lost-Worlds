package lostworlds.server.block.tree;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.biome.features.configured.TreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SequoiaTree extends AbstractMegaTreeGrower {
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random rand, boolean big) {
		return Holder.direct(TreeFeatures.BABY_SEQUOIA_TREE.get());
	}

	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random rand) {
		return Holder.direct(TreeFeatures.SEQUOIA_TREE.get());
	}
}
