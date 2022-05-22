package lostworlds.server.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.IPlantable;

public class DriedSoilBlock extends Block {
	public DriedSoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (!isNearWater(world, pos) && !world.isRainingAt(pos.above())) {
			return;
		} else {
			turnToDirt(state, world, pos);
		}
	}

	private static boolean isNearWater(LevelReader render, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (render.getFluidState(blockpos).is(FluidTags.WATER)) {
				return true;
			}
		}

		return FarmlandWaterManager.hasBlockWaterTicket(render, pos);
	}

	public static void turnToDirt(BlockState state, Level world, BlockPos pos) {
		world.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
		return true;
	}
}
