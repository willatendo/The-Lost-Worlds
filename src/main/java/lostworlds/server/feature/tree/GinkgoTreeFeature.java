package lostworlds.server.feature.tree;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GinkgoTreeFeature extends Feature<NoneFeatureConfiguration> {
	public GinkgoTreeFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	private boolean isAirOrLeaves(LevelSimulatedReader level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.is(BlockTags.LEAVES);
		});
	}

	private boolean isOnDirt(LevelSimulatedReader level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) -> {
			return isDirt(state) || state.getBlock() == Blocks.FARMLAND;
		});
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = new Random();

		if (this.isOnDirt(level, pos.below())) {
			Direction[] horizontal = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
			BlockState log = LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LOG).get().getDefaultState();
			BlockState leaves = LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LEAVES).get().getDefaultState();

			while (pos.getY() > 1 && isAirOrLeaves(level, pos)) {
				pos = pos.below();
			}

			// Trunk
			int height = 9 + rand.nextInt(6);

			if (pos.getY() >= 1 && pos.getY() + 7 + 1 < level.getHeight()) {
				for (int y = pos.getY() + 1; y < pos.getY() + height + 1; y++) {
					this.placeBlock(new BlockPos(pos.getX(), y, pos.getZ()), log, level);
				}
				for (int y = pos.getY() + 1; y < (pos.getY() + (height / 2)) + 1; y++) {
					this.placeBlock(new BlockPos(pos.getX(), y, pos.north(1).getZ()), log, level);
					this.placeBlock(new BlockPos(pos.getX(), y, pos.south(1).getZ()), log, level);
					this.placeBlock(new BlockPos(pos.east(1).getX(), y, pos.getZ()), log, level);
					this.placeBlock(new BlockPos(pos.west(1).getX(), y, pos.getZ()), log, level);
				}
			} else {
				return false;
			}

			// Branches
			this.genBranch(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), true, level, log, leaves, rand);

			if ((height / 2) > 4) {
				this.genBranch(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), true, level, log, leaves, rand);
			}

			this.genBranch(new BlockPos(pos.getX(), pos.getY() + ((height / 2) + 2), pos.getZ()), false, level, log, leaves, rand);

			if (height > 12) {
				this.genBranch(new BlockPos(pos.getX(), pos.getY() + ((height / 2) + 5), pos.getZ()), false, level, log, leaves, rand);
			}

			// Leaves
			this.placeBlock(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), leaves, level);
			this.placeBlock(new BlockPos(pos.getX(), pos.getY() + height + 2, pos.getZ()), leaves, level);
			for (Direction directions : horizontal) {
				this.placeBlock(new BlockPos(pos.relative(directions, 1).getX(), pos.getY() + height + 1, pos.getZ()), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(new BlockPos(pos.relative(directions, 1).getX(), pos.getY() + height + 1, pos.relative(directions, 1).getZ()), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
			}
			return true;
		}

		return false;
	}

	public void genBranch(BlockPos pos, boolean large, WorldGenLevel level, BlockState log, BlockState leaves, Random rand) {
		Direction[] horizontal = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
		if (large) {
			for (Direction directions : horizontal) {
				int length = 2 + rand.nextInt(2);
				for (int l = 0; l < length; l++) {
					this.placeBlock(pos.relative(directions, l + 2).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), level);
					if (l == (length / 2)) {
						int chance = rand.nextInt(4);
						if (chance >= 1 && chance <= 2) {
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getClockWise(), 1).above(2), log.setValue(RotatedPillarBlock.AXIS, directions.getClockWise().getAxis()), level);
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getClockWise(), 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
						}
						if (chance >= 2 && chance <= 3) {
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getCounterClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getCounterClockWise(), 1).above(2), log.setValue(RotatedPillarBlock.AXIS, directions.getCounterClockWise().getAxis()), level);
							this.placeBlock(pos.relative(directions, l + 2).relative(directions.getCounterClockWise(), 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
						}
					}
				}

				this.placeBlock(pos.relative(directions, length + 2).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length + 3).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length + 2).above(2), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), level);
				this.placeBlock(pos.relative(directions, length + 1).relative(directions.getClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length + 1).relative(directions.getCounterClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);

			}
		} else {
			for (Direction directions : horizontal) {
				int length = 1 + rand.nextInt(1);
				for (int l = 0; l < length; l++) {
					this.placeBlock(pos.relative(directions, l + 1).above(1), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), level);
				}
				this.placeBlock(pos.relative(directions, length + 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length + 2).above(2), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length + 1).above(2), log.setValue(RotatedPillarBlock.AXIS, directions.getAxis()), level);
				this.placeBlock(pos.relative(directions, length).relative(directions.getClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
				this.placeBlock(pos.relative(directions, length).relative(directions.getCounterClockWise(), 1).above(1), leaves.setValue(LeavesBlock.DISTANCE, 2), level);
			}
		}
	}

	public void placeBlock(BlockPos pos, BlockState state, WorldGenLevel level) {
		level.setBlock(pos, state, 3);
	}
}
