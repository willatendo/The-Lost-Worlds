package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
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
		int drop = rand.nextInt(5);
		if (drop == 0) {
			Egg egg = state.getValue(EGG);
			if (egg == Egg.TINY) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.TINY_FOSSILISED_EGG.getDefaultState().setValue(TinyFossilizedEggBlock.EGGS, Integer.valueOf(rand.nextInt(19) + 1)));
			} else if (egg == Egg.SMALL) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.SMALL_FOSSILISED_EGG.getDefaultState().setValue(SmallFossilizedEggBlock.EGGS, Integer.valueOf(rand.nextInt(10) + 1)));
			} else if (egg == Egg.MEDIUM) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.MEDIUM_FOSSILISED_EGG.getDefaultState().setValue(MediumFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(6) + 1)));
			} else {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.LARGE_FOSSILISED_EGG.getDefaultState().setValue(LargeFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(3) + 1)));
			}
		} else if (drop == 1) {
			this.popResource(world, pos, LostWorldsItems.FOSSILIZED_FEATHER.get().getDefaultInstance());
		} else if (drop == 2) {
			this.popResource(world, pos, LostWorldsItems.FOSSILIZED_SKIN_IMPRESSION.get().getDefaultInstance());
		} else if (drop == 3) {
			int feather = rand.nextInt(DinoTypes.feathered().size());
			this.popResource(world, pos, DinoTypes.feathered().get(feather).getFeather().get().getDefaultInstance());
		} else if (drop == 4) {
			this.popResource(world, pos, DinoTypes.NAUTILUS.getExtraBlock().get().asItem().getDefaultInstance());
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(EGG);
	}
}
