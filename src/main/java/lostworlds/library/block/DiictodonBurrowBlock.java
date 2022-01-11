package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.EntityInit;
import lostworlds.library.block.properties.ModBlockStateProperties;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DiictodonBurrowBlock extends Block {
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	public static final IntegerProperty EGGS = ModBlockStateProperties.BURROW_EGGS;

	public DiictodonBurrowBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(0)));
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (this.shouldUpdateHatchLevel(world) && state.getValue(EGGS) < 0) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				world.playSound((PlayerEntity) null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
				world.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
			} else {
				world.playSound((PlayerEntity) null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);

				for (int j = 0; j < state.getValue(EGGS); ++j) {
					world.levelEvent(2001, pos, Block.getId(state));
					PrehistoricEntity entity = EntityInit.DIICTODON.create(world);
					entity.moveTo((double) pos.getX() + 0.3D * 0.2D, (double) pos.getY() + 16.0F, (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(entity);
					entity.setAge(-24000);
				}
			}
		}
	}

	private boolean shouldUpdateHatchLevel(World world) {
		float f = world.getTimeOfDay(1.0F);
		if ((double) f < 0.69D && (double) f > 0.65D) {
			return true;
		} else {
			return world.random.nextInt(500) == 0;
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(HATCH, EGGS);
		super.createBlockStateDefinition(builder);
	}
}
