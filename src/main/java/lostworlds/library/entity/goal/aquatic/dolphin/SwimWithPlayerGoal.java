package lostworlds.library.entity.goal.aquatic.dolphin;

import java.util.EnumSet;

import lostworlds.library.entity.aquatic.DolphinLikeEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SwimWithPlayerGoal extends Goal {
	private final DolphinLikeEntity entity;
	private final double speedModifier;
	private PlayerEntity player;

	public SwimWithPlayerGoal(DolphinLikeEntity entity, double speedModifer) {
		this.entity = entity;
		this.speedModifier = speedModifer;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		this.player = this.entity.level.getNearestPlayer(DolphinLikeEntity.SWIM_WITH_PLAYER_TARGETING, this.entity);
		if (this.player == null) {
			return false;
		} else {
			return this.player.isSwimming() && this.entity.getTarget() != this.player;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return this.player != null && this.player.isSwimming() && this.entity.distanceToSqr(this.player) < 256.0D;
	}

	@Override
	public void start() {
		this.player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
	}

	@Override
	public void stop() {
		this.player = null;
		this.entity.getNavigation().stop();
	}

	@Override
	public void tick() {
		this.entity.getLookControl().setLookAt(this.player, (float) (this.entity.getMaxHeadYRot() + 20), (float) this.entity.getMaxHeadXRot());
		if (this.entity.distanceToSqr(this.player) < 6.25D) {
			this.entity.getNavigation().stop();
		} else {
			this.entity.getNavigation().moveTo(this.player, this.speedModifier);
		}

		if (this.player.isSwimming() && this.player.level.random.nextInt(6) == 0) {
			this.player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
		}
	}
}
