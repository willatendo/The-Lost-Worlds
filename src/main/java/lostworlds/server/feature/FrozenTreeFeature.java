package lostworlds.server.feature;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

public class FrozenTreeFeature extends Feature<TreeConfiguration> {
	public FrozenTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	public static boolean isFree(WorldGenLevel world, LevelSimulatedReader reader, BlockPos pos) {
		return validTreePos(world, reader, pos) || reader.isStateAtPosition(pos, (blockstate) -> {
			return blockstate.is(BlockTags.LOGS);
		});
	}

	private static boolean isVine(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (blockstate) -> {
			return blockstate.is(Blocks.VINE);
		});
	}

	private static boolean isBlockWater(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (blockstate) -> {
			return blockstate.is(Blocks.WATER);
		});
	}

	public static boolean isAirOrLeaves(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (blockstate) -> {
			return blockstate.isAir() || blockstate.is(BlockTags.LEAVES);
		});
	}

	private static boolean isValidPlacement(WorldGenLevel world, LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (blockstate) -> {
			Block block = blockstate.getBlock();
			return block == Blocks.SNOW_BLOCK && world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK && world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK;
		});
	}

	private static boolean isReplaceablePlant(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (blockstate) -> {
			Material material = blockstate.getMaterial();
			return material == Material.REPLACEABLE_PLANT;
		});
	}

	public static void setBlockKnownShape(LevelWriter writer, BlockPos reader, BlockState state) {
		writer.setBlock(reader, state, 19);
	}

	public static boolean validTreePos(WorldGenLevel world, LevelSimulatedReader reader, BlockPos pos) {
		return isAirOrLeaves(reader, pos) || isReplaceablePlant(reader, pos) || isBlockWater(reader, pos) || isValidPlacement(world, reader, pos);
	}

	private boolean doPlace(WorldGenLevel world, LevelSimulatedRW reader, Random rand, BlockPos pos, Set<BlockPos> blockPos1, Set<BlockPos> blockPos2, BoundingBox box, TreeConfiguration config) {
		int i = config.trunkPlacer.getTreeHeight(rand);
		int j = config.foliagePlacer.foliageHeight(rand, i, config);
		int k = i - j;
		int l = config.foliagePlacer.foliageRadius(rand, k);
		BlockPos blockpos;
		if (!config.fromSapling) {
			int i1 = reader.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, pos).getY();
			int j1 = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, pos).getY();
			if (j1 - i1 > config.maxWaterDepth) {
				return false;
			}

			int k1;
			if (config.heightmap == Heightmap.Types.OCEAN_FLOOR) {
				k1 = i1;
			} else if (config.heightmap == Heightmap.Types.WORLD_SURFACE) {
				k1 = j1;
			} else {
				k1 = reader.getHeightmapPos(config.heightmap, pos).getY();
			}

			blockpos = new BlockPos(pos.getX(), k1, pos.getZ());
		} else {
			blockpos = pos;
		}

		if (blockpos.getY() >= 1 && blockpos.getY() + i + 1 <= 256) {
			if (!isValidPlacement(world, reader, blockpos.below())) {
				return false;
			} else {
				OptionalInt optionalint = config.minimumSize.minClippedHeight();
				int l1 = this.getMaxFreeTreeHeight(world, reader, i, blockpos, config);
				if (l1 >= i || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
					List<FoliagePlacer.FoliageAttachment> list = config.trunkPlacer.placeTrunk(reader, rand, l1, blockpos, blockPos1, box, config);
					list.forEach((blockstate) -> {
						config.foliagePlacer.createFoliage(reader, rand, config, l1, blockstate, j, l, blockPos2, box);
					});
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private int getMaxFreeTreeHeight(WorldGenLevel world, LevelSimulatedReader reader, int seed, BlockPos pos, TreeConfiguration config) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for (int i = 0; i <= seed + 1; ++i) {
			int j = config.minimumSize.getSizeAtHeight(seed, i);

			for (int k = -j; k <= j; ++k) {
				for (int l = -j; l <= j; ++l) {
					blockpos$mutable.setWithOffset(pos, k, i, l);
					if (!isFree(world, reader, blockpos$mutable) || !config.ignoreVines && isVine(reader, blockpos$mutable)) {
						return i - 2;
					}
				}
			}
		}

		return seed;
	}

	protected void setBlock(LevelWriter writer, BlockPos pos, BlockState state) {
		setBlockKnownShape(writer, pos, state);
	}

	public final boolean place(WorldGenLevel reader, ChunkGenerator chunkgenerator, Random rand, BlockPos pos, TreeConfiguration config) {
		Set<BlockPos> set = Sets.newHashSet();
		Set<BlockPos> set1 = Sets.newHashSet();
		Set<BlockPos> set2 = Sets.newHashSet();
		BoundingBox mutableboundingbox = BoundingBox.getUnknownBox();
		boolean flag = this.doPlace(reader, reader, rand, pos, set, set1, mutableboundingbox, config);
		if (mutableboundingbox.x0 <= mutableboundingbox.x1 && flag && !set.isEmpty()) {
			if (!config.decorators.isEmpty()) {
				List<BlockPos> list = Lists.newArrayList(set);
				List<BlockPos> list1 = Lists.newArrayList(set1);
				list.sort(Comparator.comparingInt(Vec3i::getY));
				list1.sort(Comparator.comparingInt(Vec3i::getY));
				config.decorators.forEach((blockstate) -> {
					blockstate.place(reader, rand, list, list1, set2, mutableboundingbox);
				});
			}

			DiscreteVoxelShape voxelshapepart = this.updateLeaves(reader, mutableboundingbox, set, set2);
			StructureTemplate.updateShapeAtEdge(reader, 3, voxelshapepart, mutableboundingbox.x0, mutableboundingbox.y0, mutableboundingbox.z0);
			return true;
		} else {
			return false;
		}
	}

	private DiscreteVoxelShape updateLeaves(LevelAccessor world, BoundingBox box, Set<BlockPos> blockPosSet1, Set<BlockPos> blockPosSet2) {
		List<Set<BlockPos>> list = Lists.newArrayList();
		DiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(box.getXSpan(), box.getYSpan(), box.getZSpan());

		for (int j = 0; j < 6; ++j) {
			list.add(Sets.newHashSet());
		}

		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for (BlockPos blockpos : Lists.newArrayList(blockPosSet2)) {
			if (box.isInside(blockpos)) {
				voxelshapepart.setFull(blockpos.getX() - box.x0, blockpos.getY() - box.y0, blockpos.getZ() - box.z0, true, true);
			}
		}

		for (BlockPos blockpos1 : Lists.newArrayList(blockPosSet1)) {
			if (box.isInside(blockpos1)) {
				voxelshapepart.setFull(blockpos1.getX() - box.x0, blockpos1.getY() - box.y0, blockpos1.getZ() - box.z0, true, true);
			}

			for (Direction direction : Direction.values()) {
				blockpos$mutable.setWithOffset(blockpos1, direction);
				if (!blockPosSet1.contains(blockpos$mutable)) {
					BlockState blockstate = world.getBlockState(blockpos$mutable);
					if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
						list.get(0).add(blockpos$mutable.immutable());
						setBlockKnownShape(world, blockpos$mutable, blockstate.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(1)));
						if (box.isInside(blockpos$mutable)) {
							voxelshapepart.setFull(blockpos$mutable.getX() - box.x0, blockpos$mutable.getY() - box.y0, blockpos$mutable.getZ() - box.z0, true, true);
						}
					}
				}
			}
		}

		for (int l = 1; l < 6; ++l) {
			Set<BlockPos> set = list.get(l - 1);
			Set<BlockPos> set1 = list.get(l);

			for (BlockPos blockpos2 : set) {
				if (box.isInside(blockpos2)) {
					voxelshapepart.setFull(blockpos2.getX() - box.x0, blockpos2.getY() - box.y0, blockpos2.getZ() - box.z0, true, true);
				}

				for (Direction direction1 : Direction.values()) {
					blockpos$mutable.setWithOffset(blockpos2, direction1);
					if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
						BlockState blockstate1 = world.getBlockState(blockpos$mutable);
						if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
							int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
							if (k > l + 1) {
								BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(l + 1));
								setBlockKnownShape(world, blockpos$mutable, blockstate2);
								if (box.isInside(blockpos$mutable)) {
									voxelshapepart.setFull(blockpos$mutable.getX() - box.x0, blockpos$mutable.getY() - box.y0, blockpos$mutable.getZ() - box.z0, true, true);
								}

								set1.add(blockpos$mutable.immutable());
							}
						}
					}
				}
			}
		}

		return voxelshapepart;
	}
}
