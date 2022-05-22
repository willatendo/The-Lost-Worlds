package lostworlds.server.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModOreRotatedPillerBlock extends RotatedPillarBlock {
	public ModOreRotatedPillerBlock(Properties properties) {
		super(properties);
	}

	protected int xpOnDrop(Random rand) {
		return this == LostWorldsBlocks.BASALT_DIAMOND_ORE.get() ? Mth.nextInt(rand, 0, 1) : 0;
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.xpOnDrop(RANDOM) : 0;
	}
}
