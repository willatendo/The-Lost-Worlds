package lostworlds.server.block;

import static lostworlds.api.APIRegistry.SOFT_DIRT_FUNCTIONS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class SoftDirtBlock extends Block {
	public static final EnumProperty<Egg> EGG = EnumProperty.create("egg", Egg.class);

	public SoftDirtBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGG, Egg.TINY));
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		int drop = rand.nextInt(SOFT_DIRT_FUNCTIONS.size());
		SOFT_DIRT_FUNCTIONS.get(drop).doFunction(state, world, pos, stack);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(EGG);
	}
}
