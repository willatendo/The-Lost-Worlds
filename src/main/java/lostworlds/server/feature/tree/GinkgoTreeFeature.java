package lostworlds.server.feature.tree;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GinkgoTreeFeature extends Feature<NoFeatureConfig> {
	public GinkgoTreeFeature(Codec<NoFeatureConfig> config) {
		super(config);
	}

	private boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		if (!(reader instanceof IWorldReader)) {
			return reader.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.LEAVES));
		} else {
			return reader.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos)) && reader.isStateAtPosition(pos, state -> state.canBeReplacedByLogs((IWorldReader) reader, pos));
		}
	}

	private boolean isOnDirt(IWorldGenerationBaseReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			Block block = state.getBlock();
			return isDirt(block) || block == Blocks.FARMLAND;
		});
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		if (this.isOnDirt(reader, pos.below())) {
			Direction[] horizontal = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
			BlockState log = LostWorldsBlocks.GINKGO_LOG.getDefaultState();
			BlockState leaves = LostWorldsBlocks.GINKGO_LEAVES.getDefaultState();

			while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
				pos = pos.below();
			}

			// Trunk
			int height = 9 + rand.nextInt(6);

			if (pos.getY() >= 1 && pos.getY() + 7 + 1 < reader.getHeight()) {
				for (int y = pos.getY() + 1; y < pos.getY() + height + 1; y++) {
					reader.setBlock(new BlockPos(pos.getX(), y, pos.getZ()), log, 3);
				}
				for (int y = pos.getY() + 1; y < (pos.getY() + (height / 2)) + 1; y++) {
					reader.setBlock(new BlockPos(pos.getX(), y, pos.north(1).getZ()), log, 3);
					reader.setBlock(new BlockPos(pos.getX(), y, pos.south(1).getZ()), log, 3);
					reader.setBlock(new BlockPos(pos.east(1).getX(), y, pos.getZ()), log, 3);
					reader.setBlock(new BlockPos(pos.west(1).getX(), y, pos.getZ()), log, 3);
				}
			} else {
				return false;
			}

			// Branches
			this.genBranch(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), true, reader, log, leaves, rand);

			if ((height / 2) > 4) {
				this.genBranch(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), true, reader, log, leaves, rand);
			}

			this.genBranch(new BlockPos(pos.getX(), pos.getY() + ((height / 2) + 2), pos.getZ()), false, reader, log, leaves, rand);

			if (height > 12) {
				this.genBranch(new BlockPos(pos.getX(), pos.getY() + ((height / 2) + 5), pos.getZ()), false, reader, log, leaves, rand);
			}

			// Leaves
			reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), leaves, 3);
			reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height + 2, pos.getZ()), leaves, 3);
			for (Direction directions : horizontal) {
				reader.setBlock(new BlockPos(pos.relative(directions, 1).getX(), pos.getY() + height + 1, pos.getZ()), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(new BlockPos(pos.relative(directions, 1).getX(), pos.getY() + height + 1, pos.relative(directions, 1).getZ()), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
			}
			return true;
		}

		return false;
	}

	public void genBranch(BlockPos pos, boolean large, ISeedReader reader, BlockState log, BlockState leaves, Random rand) {
		Direction[] horizontal = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
		if (large) {
			for (Direction directions : horizontal) {
				int length = 2 + rand.nextInt(2);
				for (int l = 0; l < length; l++) {
					reader.setBlock(pos.relative(directions, l + 2).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), 3);
					if (l == (length / 2)) {
						int chance = rand.nextInt(4);
						if (chance >= 1 && chance <= 2) {
							reader.setBlock(pos.relative(directions, l + 2).relative(directions.getClockWise(), 1).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getClockWise().getAxis()), 3);
							reader.setBlock(pos.relative(directions, l + 2).relative(directions.getClockWise(), 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
						}
						if (chance >= 2 && chance <= 3) {
							reader.setBlock(pos.relative(directions, l + 2).relative(directions.getCounterClockWise(), 1).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getCounterClockWise().getAxis()), 3);
							reader.setBlock(pos.relative(directions, l + 2).relative(directions.getCounterClockWise(), 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
						}
					}
				}
				reader.setBlock(pos.relative(directions, length + 2).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length + 3).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length + 1).relative(directions.getClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length + 1).relative(directions.getCounterClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
			}
		} else {
			for (Direction directions : horizontal) {
				int length = 1 + rand.nextInt(1);
				for (int l = 0; l < length; l++) {
					reader.setBlock(pos.relative(directions, l + 1).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), 3);
				}
				reader.setBlock(pos.relative(directions, length + 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length + 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length).relative(directions.getClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
				reader.setBlock(pos.relative(directions, length).relative(directions.getCounterClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), 3);
			}
		}
	}
}
