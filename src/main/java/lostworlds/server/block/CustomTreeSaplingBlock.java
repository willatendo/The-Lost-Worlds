package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.block.tree.CustomTree;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;

public class CustomTreeSaplingBlock extends SaplingBlock {
	private final CustomTree tree;

	public CustomTreeSaplingBlock(CustomTree tree, Properties properties) {
		super(null, properties);
		this.tree = tree;
	}

	@Override
	public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
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
