package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.block.properties.ModBlockStateProperties;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.terrestrial.PrehistoricMob;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DiictodonBurrowBlock extends Block {
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	public static final IntegerProperty EGGS = ModBlockStateProperties.BURROW_EGGS;

	public DiictodonBurrowBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(0)));
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (this.shouldUpdateHatchLevel(world) && state.getValue(EGGS) < 0) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				world.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
				world.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
			} else {
				world.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);

				for (int j = 0; j < state.getValue(EGGS); ++j) {
					world.levelEvent(2001, pos, Block.getId(state));
					PrehistoricMob entity = LostWorldsEntities.DIICTODON.create(world);
					entity.moveTo((double) pos.getX() + 0.3D * 0.2D, (double) pos.getY() + 16.0F, (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(entity);
					entity.setAge(-24000);
				}
			}
		}
	}

	private boolean shouldUpdateHatchLevel(Level world) {
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
