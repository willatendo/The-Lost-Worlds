package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.block.tree.CustomTree;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

public class CustomTreeSaplingBlock extends SaplingBlock {
	private final CustomTree tree;

	public CustomTreeSaplingBlock(CustomTree tree, Properties properties) {
		super(null, properties);
		this.tree = tree;
	}

	@Override
	public void advanceTree(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		if (state.getValue(STAGE) == 0) {
			world.setBlock(pos, state.cycle(STAGE), 4);
		} else {
			if (!ForgeEventFactory.saplingGrowTree(world, rand, pos)) {
				return;
			}
			this.tree.place(world, world.getChunkSource().getGenerator(), pos, state, rand);
		}
	}
}
