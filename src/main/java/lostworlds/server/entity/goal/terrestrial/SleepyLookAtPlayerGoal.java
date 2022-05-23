package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public class SleepyLookAtPlayerGoal extends Goal {
	protected final EggLayingMob entity;
	protected Entity lookAt;
	protected final float lookDistance;
	private int lookTime;
	protected final float probability;
	protected final Class<? extends LivingEntity> lookAtType;
	protected final TargetingConditions lookAtContext;

	public SleepyLookAtPlayerGoal(EggLayingMob entity, Class<? extends LivingEntity> lookAtEntity, float lookDistance) {
		this(entity, lookAtEntity, lookDistance, 0.02F);
	}

	public SleepyLookAtPlayerGoal(EggLayingMob entity, Class<? extends LivingEntity> lookAtEntity, float lookDistance, float probability) {
		this.entity = entity;
		this.lookAtType = lookAtEntity;
		this.lookDistance = lookDistance;
		this.probability = probability;
		this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		if (lookAtEntity == Player.class) {
			this.lookAtContext = TargetingConditions.forNonCombat().range((double) lookDistance).selector((living) -> {
				return EntitySelector.notRiding(entity).test(living);
			});
		} else {
			this.lookAtContext = TargetingConditions.forNonCombat().range((double) lookDistance);
		}

	}

	@Override
	public boolean canUse() {
		if (this.entity.getRandom().nextFloat() >= this.probability) {
			return false;
		} else if (this.entity.isSleeping()) {
			return false;
		} else {
			if (this.entity.getTarget() != null) {
				this.lookAt = this.entity.getTarget();
			}

			if (this.lookAtType == Player.class) {
				this.lookAt = this.entity.level.getNearestPlayer(this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
			} else {
				this.lookAt = this.entity.level.getNearestEntity(this.lookAtType, this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ(), this.entity.getBoundingBox().inflate((double) this.lookDistance, 3.0D, (double) this.lookDistance));
			}

			return this.lookAt != null;
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (!this.lookAt.isAlive()) {
			return false;
		} else if (this.entity.distanceToSqr(this.lookAt) > (double) (this.lookDistance * this.lookDistance)) {
			return false;
		} else {
			return this.lookTime > 0;
		}
	}

	@Override
	public void start() {
		this.lookTime = 40 + this.entity.getRandom().nextInt(40);
	}

	@Override
	public void stop() {
		this.lookAt = null;
	}

	@Override
	public void tick() {
		this.entity.getLookControl().setLookAt(this.lookAt.getX(), this.lookAt.getEyeY(), this.lookAt.getZ());
		--this.lookTime;
	}
}
