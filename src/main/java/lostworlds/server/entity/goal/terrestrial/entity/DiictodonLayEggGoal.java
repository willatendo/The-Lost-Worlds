package lostworlds.server.entity.goal.terrestrial.entity;

import java.util.Random;

import lostworlds.server.block.DiictodonBurrowBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;

public class DiictodonLayEggGoal extends MoveToBlockGoal {
	private final EggLayingMob entity;

	public DiictodonLayEggGoal(EggLayingMob entity, double speedModifer) {
		super(entity, speedModifer, 16);
		this.entity = entity;
	}

	@Override
	public boolean canUse() {
		return this.entity.hasEgg() && this.entity.getHomePos().closerToCenterThan(this.entity.position(), 9.0D) ? super.canUse() : false;
	}

	@Override
	public void stop() {
		super.stop();
		this.entity.setGoingHome(false);
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && this.entity.hasEgg() && this.entity.getHomePos().closerToCenterThan(this.entity.position(), 9.0D);
	}

	@Override
	public void tick() {
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if (!this.entity.isInWater() && this.isReachedTarget()) {
			Level world = this.entity.level;
			world.playSound((Player) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
			Block block = LostWorldsBlocks.DIICTODON_BURROW.getDefaultState().setValue(DiictodonBurrowBlock.EGGS, Integer.valueOf(new Random().nextInt(19) + 1)).getBlock();
			world.setBlock(this.blockPos.below(), block.defaultBlockState(), 3);
			this.entity.setHasEgg(false);
			this.entity.setInLoveTime(600);
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
		return isNatural(reader, pos);
	}

	public static boolean isNatural(BlockGetter reader, BlockPos pos) {
		return reader.getBlockState(pos).is(LostWorldsBlocks.PERMIAN_SAND.get()) || reader.getBlockState(pos).is(LostWorldsBlocks.DIICTODON_BURROW.get());
	}
}
