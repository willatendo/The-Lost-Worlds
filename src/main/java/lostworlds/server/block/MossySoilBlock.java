package lostworlds.server.block;

import java.util.List;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraftforge.common.IPlantable;

public class MossySoilBlock extends Block implements BonemealableBlock {
	public MossySoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		if (!level.isClientSide()) {
			if (!level.isAreaLoaded(pos, 3))
				return;
			if (!isLightEnough(state, level, pos)) {
				level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			} else if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
				BlockState blockstate = this.defaultBlockState();

				for (int i = 0; i < 4; ++i) {
					BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (level.getBlockState(blockpos).getBlock() == Blocks.DIRT && isValidBonemealTargetGrass(blockstate, level, blockpos)) {
						level.setBlockAndUpdate(blockpos, blockstate);
					}
				}
			}
		}
	}

	private static boolean isLightEnough(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = level.getBlockState(blockpos);

		int i = LayerLightEngine.getLightBlockInto(level, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(level, blockpos));
		return i < level.getMaxLightLevel();
	}

	private static boolean isValidBonemealTargetGrass(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return isLightEnough(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction facing, IPlantable plantable) {
		return true;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = Blocks.GRASS.defaultBlockState();

		bonemealLoop: for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1)) {
					continue bonemealLoop;
				}
			}

			BlockState blockstate1 = level.getBlockState(blockpos1);
			if (blockstate1.is(blockstate.getBlock()) && rand.nextInt(10) == 0) {
				((BonemealableBlock) blockstate.getBlock()).performBonemeal(level, rand, blockpos1, blockstate1);
			}

			if (blockstate1.isAir()) {
				Holder<PlacedFeature> holder;
				if (rand.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = level.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					holder = ((RandomPatchConfiguration) list.get(0).config()).feature();
				} else {
					holder = VegetationPlacements.GRASS_BONEMEAL;
				}

				holder.value().place(level, level.getChunkSource().getGenerator(), rand, blockpos1);
			}
		}

	}
}
