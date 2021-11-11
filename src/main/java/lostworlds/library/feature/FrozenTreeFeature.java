package lostworlds.library.feature;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

public class FrozenTreeFeature extends Feature<BaseTreeFeatureConfig> 
{
	public FrozenTreeFeature(Codec<BaseTreeFeatureConfig> config) 
	{
		super(config);
	}
	
	public static boolean isFree(ISeedReader world, IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return validTreePos(world, reader, pos) || reader.isStateAtPosition(pos, (blockstate) -> 
		{
			return blockstate.is(BlockTags.LOGS);
		});
	}

	private static boolean isVine(IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return reader.isStateAtPosition(pos, (blockstate) -> 
		{
			return blockstate.is(Blocks.VINE);
		});
	}

	private static boolean isBlockWater(IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return reader.isStateAtPosition(pos, (blockstate) -> 
		{
			return blockstate.is(Blocks.WATER);
		});
	}

	public static boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return reader.isStateAtPosition(pos, (blockstate) -> 
		{
			return blockstate.isAir() || blockstate.is(BlockTags.LEAVES);
		});
	}

	private static boolean isValidPlacement(ISeedReader world, IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return reader.isStateAtPosition(pos, (blockstate) -> 
		{
			Block block = blockstate.getBlock();
			return block == Blocks.SNOW_BLOCK && world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK && world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK;
		});
	}

	private static boolean isReplaceablePlant(IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return reader.isStateAtPosition(pos, (blockstate) -> 
		{
			Material material = blockstate.getMaterial();
			return material == Material.REPLACEABLE_PLANT;
		});
	}

	public static void setBlockKnownShape(IWorldWriter writer, BlockPos reader, BlockState state) 
	{
		writer.setBlock(reader, state, 19);
	}

	public static boolean validTreePos(ISeedReader world, IWorldGenerationBaseReader reader, BlockPos pos) 
	{
		return isAirOrLeaves(reader, pos) || isReplaceablePlant(reader, pos) || isBlockWater(reader, pos) || isValidPlacement(world, reader, pos);
	}

	private boolean doPlace(ISeedReader world, IWorldGenerationReader reader, Random rand, BlockPos pos, Set<BlockPos> blockPos1, Set<BlockPos> blockPos2, MutableBoundingBox box, BaseTreeFeatureConfig config) 
	{
		int i = config.trunkPlacer.getTreeHeight(rand);
		int j = config.foliagePlacer.foliageHeight(rand, i, config);
		int k = i - j;
		int l = config.foliagePlacer.foliageRadius(rand, k);
		BlockPos blockpos;
		if(!config.fromSapling) 
		{
			int i1 = reader.getHeightmapPos(Heightmap.Type.OCEAN_FLOOR, pos).getY();
			int j1 = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE, pos).getY();
			if(j1 - i1 > config.maxWaterDepth) 
			{
				return false;
			}

			int k1;
			if(config.heightmap == Heightmap.Type.OCEAN_FLOOR) 
			{
				k1 = i1;
			} 
			else if(config.heightmap == Heightmap.Type.WORLD_SURFACE) 
			{
				k1 = j1;
			} 
			else 
			{
				k1 = reader.getHeightmapPos(config.heightmap, pos).getY();
			}

			blockpos = new BlockPos(pos.getX(), k1, pos.getZ());
		} 
		else 
		{
			blockpos = pos;
		}

		if(blockpos.getY() >= 1 && blockpos.getY() + i + 1 <= 256) 
		{
			if(!isValidPlacement(world, reader, blockpos.below())) 
			{
				return false;
			} 
			else 
			{
				OptionalInt optionalint = config.minimumSize.minClippedHeight();
				int l1 = this.getMaxFreeTreeHeight(world, reader, i, blockpos, config);
				if(l1 >= i || optionalint.isPresent() && l1 >= optionalint.getAsInt()) 
				{
					List<FoliagePlacer.Foliage> list = config.trunkPlacer.placeTrunk(reader, rand, l1, blockpos, blockPos1, box, config);
					list.forEach((blockstate) -> 
					{
						config.foliagePlacer.createFoliage(reader, rand, config, l1, blockstate, j, l, blockPos2, box);
					});
					return true;
				} 
				else 
				{
					return false;
				}
			}
		} 
		else 
		{
			return false;
		}
	}

	private int getMaxFreeTreeHeight(ISeedReader world, IWorldGenerationBaseReader reader, int seed, BlockPos pos, BaseTreeFeatureConfig config) 
	{
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for(int i = 0; i <= seed + 1; ++i) 
		{
			int j = config.minimumSize.getSizeAtHeight(seed, i);

			for(int k = -j; k <= j; ++k) 
			{
				for(int l = -j; l <= j; ++l) 
				{
					blockpos$mutable.setWithOffset(pos, k, i, l);
					if(!isFree(world, reader, blockpos$mutable) || !config.ignoreVines && isVine(reader, blockpos$mutable)) 
					{
						return i - 2;
					}
				}
			}
		}

		return seed;
	}

	protected void setBlock(IWorldWriter writer, BlockPos pos, BlockState state) 
	{
		setBlockKnownShape(writer, pos, state);
	}

	public final boolean place(ISeedReader reader, ChunkGenerator chunkgenerator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) 
	{
		Set<BlockPos> set = Sets.newHashSet();
		Set<BlockPos> set1 = Sets.newHashSet();
		Set<BlockPos> set2 = Sets.newHashSet();
		MutableBoundingBox mutableboundingbox = MutableBoundingBox.getUnknownBox();
		boolean flag = this.doPlace(reader, reader, rand, pos, set, set1, mutableboundingbox, config);
		if(mutableboundingbox.x0 <= mutableboundingbox.x1 && flag && !set.isEmpty()) 
		{
			if(!config.decorators.isEmpty()) 
			{
				List<BlockPos> list = Lists.newArrayList(set);
				List<BlockPos> list1 = Lists.newArrayList(set1);
				list.sort(Comparator.comparingInt(Vector3i::getY));
				list1.sort(Comparator.comparingInt(Vector3i::getY));
				config.decorators.forEach((blockstate) -> 
				{
					blockstate.place(reader, rand, list, list1, set2, mutableboundingbox);
				});
			}

			VoxelShapePart voxelshapepart = this.updateLeaves(reader, mutableboundingbox, set, set2);
			Template.updateShapeAtEdge(reader, 3, voxelshapepart, mutableboundingbox.x0, mutableboundingbox.y0,
					mutableboundingbox.z0);
			return true;
		} 
		else 
		{
			return false;
		}
	}

	private VoxelShapePart updateLeaves(IWorld world, MutableBoundingBox box, Set<BlockPos> blockPosSet1, Set<BlockPos> blockPosSet2) 
	{
		List<Set<BlockPos>> list = Lists.newArrayList();
		VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(box.getXSpan(), box.getYSpan(), box.getZSpan());

		for(int j = 0; j < 6; ++j) 
		{
			list.add(Sets.newHashSet());
		}

		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for(BlockPos blockpos : Lists.newArrayList(blockPosSet2)) 
		{
			if(box.isInside(blockpos)) 
			{
				voxelshapepart.setFull(blockpos.getX() - box.x0, blockpos.getY() - box.y0, blockpos.getZ() - box.z0, true, true);
			}
		}

		for(BlockPos blockpos1 : Lists.newArrayList(blockPosSet1)) 
		{
			if(box.isInside(blockpos1)) 
			{
				voxelshapepart.setFull(blockpos1.getX() - box.x0, blockpos1.getY() - box.y0, blockpos1.getZ() - box.z0, true, true);
			}

			for(Direction direction : Direction.values()) 
			{
				blockpos$mutable.setWithOffset(blockpos1, direction);
				if(!blockPosSet1.contains(blockpos$mutable)) 
				{
					BlockState blockstate = world.getBlockState(blockpos$mutable);
					if(blockstate.hasProperty(BlockStateProperties.DISTANCE)) 
					{
						list.get(0).add(blockpos$mutable.immutable());
						setBlockKnownShape(world, blockpos$mutable, blockstate.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(1)));
						if(box.isInside(blockpos$mutable)) 
						{
							voxelshapepart.setFull(blockpos$mutable.getX() - box.x0, blockpos$mutable.getY() - box.y0, blockpos$mutable.getZ() - box.z0, true, true);
						}
					}
				}
			}
		}

		for(int l = 1; l < 6; ++l) 
		{
			Set<BlockPos> set = list.get(l - 1);
			Set<BlockPos> set1 = list.get(l);

			for(BlockPos blockpos2 : set) 
			{
				if(box.isInside(blockpos2)) 
				{
					voxelshapepart.setFull(blockpos2.getX() - box.x0, blockpos2.getY() - box.y0, blockpos2.getZ() - box.z0, true, true);
				}

				for(Direction direction1 : Direction.values()) 
				{
					blockpos$mutable.setWithOffset(blockpos2, direction1);
					if(!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) 
					{
						BlockState blockstate1 = world.getBlockState(blockpos$mutable);
						if(blockstate1.hasProperty(BlockStateProperties.DISTANCE)) 
						{
							int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
							if(k > l + 1) 
							{
								BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(l + 1));
								setBlockKnownShape(world, blockpos$mutable, blockstate2);
								if(box.isInside(blockpos$mutable)) 
								{
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
