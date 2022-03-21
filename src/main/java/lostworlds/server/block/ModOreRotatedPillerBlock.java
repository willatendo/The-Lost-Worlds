package lostworlds.server.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class ModOreRotatedPillerBlock extends RotatedPillarBlock {
	public ModOreRotatedPillerBlock(Properties properties) {
		super(properties);
	}

	protected int xpOnDrop(Random rand) {
		return this == LostWorldsBlocks.BASALT_DIAMOND_ORE ? MathHelper.nextInt(rand, 0, 1) : 0;
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.xpOnDrop(RANDOM) : 0;
	}
}
