package lostworlds.server.feature.mod;

import com.mojang.serialization.Codec;

import lostworlds.server.feature.config.SuppliedDiskConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ModDiskFeature extends Feature<SuppliedDiskConfiguration> {
	public ModDiskFeature(Codec<SuppliedDiskConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<SuppliedDiskConfiguration> context) {
		SuppliedDiskConfiguration configuration = context.config();
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		boolean flag = false;
		int i = pos.getY();
		int j = i + configuration.halfHeight();
		int k = i - configuration.halfHeight() - 1;
		boolean flag1 = configuration.state().get().getBlock() instanceof FallingBlock;
		int l = configuration.radius().sample(context.random());

		for (int i1 = pos.getX() - l; i1 <= pos.getX() + l; ++i1) {
			for (int j1 = pos.getZ() - l; j1 <= pos.getZ() + l; ++j1) {
				int k1 = i1 - pos.getX();
				int l1 = j1 - pos.getZ();
				if (k1 * k1 + l1 * l1 <= l * l) {
					boolean flag2 = false;

					for (int i2 = j; i2 >= k; --i2) {
						BlockPos blockpos1 = new BlockPos(i1, i2, j1);
						BlockState blockstate = level.getBlockState(blockpos1);
						Block block = blockstate.getBlock();
						boolean flag3 = false;
						if (i2 > k) {
							for (BlockState blockstate1 : configuration.targets().get()) {
								if (blockstate1.is(block)) {
									level.setBlock(blockpos1, configuration.state().get(), 2);
									this.markAboveForPostProcessing(level, blockpos1);
									flag = true;
									flag3 = true;
									break;
								}
							}
						}

						if (flag1 && flag2 && blockstate.isAir()) {
							BlockState blockstate2 = configuration.state().get().is(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
							level.setBlock(new BlockPos(i1, i2 + 1, j1), blockstate2, 2);
						}

						flag2 = flag3;
					}
				}
			}
		}

		return flag;
	}
}
