package lostworlds.server.block;

import static lostworlds.api.APIRegistry.SOFT_DIRT_FUNCTIONS;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class SoftDirtBlock extends Block {
	public static final EnumProperty<Egg> EGG = EnumProperty.create("egg", Egg.class);

	public SoftDirtBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGG, Egg.TINY));
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		int drop = rand.nextInt(SOFT_DIRT_FUNCTIONS.size());
		SOFT_DIRT_FUNCTIONS.get(drop).doFunction(state, world, pos, stack);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(EGG);
	}
}
