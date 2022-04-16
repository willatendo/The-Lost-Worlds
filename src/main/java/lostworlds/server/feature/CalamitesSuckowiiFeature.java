package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class CalamitesSuckowiiFeature extends Feature<ProbabilityConfig> {
	public CalamitesSuckowiiFeature(Codec<ProbabilityConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		BlockState trunk = LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState().setValue(BambooBlock.AGE, Integer.valueOf(1)).setValue(BambooBlock.LEAVES, BambooLeaves.NONE).setValue(BambooBlock.STAGE, Integer.valueOf(0));
		BlockState finalLarge = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooBlock.STAGE, Integer.valueOf(1));
		BlockState topLarge = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE);
		BlockState topSmall = trunk.setValue(BambooBlock.LEAVES, BambooLeaves.SMALL);

		int i = 0;
		BlockPos.Mutable blockpos$mutable = pos.mutable();
		BlockPos.Mutable blockpos$mutable1 = pos.mutable();
		if (reader.isEmptyBlock(blockpos$mutable)) {
			if (LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState().canSurvive(reader, blockpos$mutable)) {
				int j = rand.nextInt(12) + 5;
				if (rand.nextFloat() < config.probability) {
					int k = rand.nextInt(4) + 1;

					for (int l = pos.getX() - k; l <= pos.getX() + k; ++l) {
						for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k; ++i1) {
							int j1 = l - pos.getX();
							int k1 = i1 - pos.getZ();
							if (j1 * j1 + k1 * k1 <= k * k) {
								blockpos$mutable1.set(l, reader.getHeight(Heightmap.Type.WORLD_SURFACE, l, i1) - 1, i1);
								if (isDirt(reader.getBlockState(blockpos$mutable1).getBlock())) {
									reader.setBlock(blockpos$mutable1, Blocks.PODZOL.defaultBlockState(), 2);
								}
							}
						}
					}
				}

				for (int l1 = 0; l1 < j && reader.isEmptyBlock(blockpos$mutable); ++l1) {
					reader.setBlock(blockpos$mutable, trunk, 2);
					blockpos$mutable.move(Direction.UP, 1);
				}

				if (blockpos$mutable.getY() - pos.getY() >= 3) {
					reader.setBlock(blockpos$mutable, finalLarge, 2);
					reader.setBlock(blockpos$mutable.move(Direction.DOWN, 1), topLarge, 2);
					reader.setBlock(blockpos$mutable.move(Direction.DOWN, 1), topSmall, 2);
				}
			}

			++i;
		}

		return i > 0;
	}
}
