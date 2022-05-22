package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;
import java.util.function.Predicate;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

public class TerrestrialEatGrassGoal extends Goal {
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.GRASS);
	private final EggLayingEntity entity;
	private final Level level;

	public TerrestrialEatGrassGoal(EggLayingEntity entity) {
		this.entity = entity;
		this.level = entity.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() {
		if (!this.entity.isHungry() || this.entity.isSleeping()) {
			return false;
		} else {
			BlockPos blockpos = this.entity.blockPosition();
			if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
				return true;
			} else {
				return this.level.getBlockState(blockpos.below()).is(Blocks.GRASS_BLOCK);
			}
		}
	}

	@Override
	public void start() {
		this.level.broadcastEntityEvent(this.entity, (byte) 10);
		this.entity.getNavigation().stop();
		this.entity.setAnimation(this.entity.ANIMATION_EAT);
	}

	@Override
	public void stop() {
		this.entity.increaseHunger(this.entity.maxHunger());
		this.entity.setAnimation(this.entity.ANIMATION_IDLE);
	}

	@Override
	public boolean canContinueToUse() {
		return this.entity.isHungry();
	}

	@Override
	public void tick() {
		if (this.entity.isHungry()) {
			BlockPos blockpos = this.entity.blockPosition();
			if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
				if (ForgeEventFactory.getMobGriefingEvent(this.level, this.entity)) {
					this.level.destroyBlock(blockpos, false);
				}
				this.entity.ate();
			} else {
				BlockPos blockpos1 = blockpos.below();
				if (this.level.getBlockState(blockpos1).is(Blocks.GRASS_BLOCK)) {
					if (ForgeEventFactory.getMobGriefingEvent(this.level, this.entity)) {
						this.level.levelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
						this.level.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
					}

					this.entity.ate();
				}
			}
		}
	}
}
