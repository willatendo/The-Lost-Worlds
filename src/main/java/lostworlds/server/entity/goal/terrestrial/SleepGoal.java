package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;

import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import lostworlds.server.entity.utils.enums.ActivityType;
import net.minecraft.world.entity.ai.goal.Goal;

public class SleepGoal extends Goal {
	private final PrehistoricEntity entity;

	public SleepGoal(PrehistoricEntity entity) {
		this.entity = entity;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}

	@Override
	public void tick() {
		super.tick();
		this.entity.setAnimation(entity.ANIMATION_SLEEP);
		this.entity.getMoveControl().setWantedPosition(this.entity.getX(), this.entity.getY(), this.entity.getZ(), 0.0D);
		this.entity.getNavigation().stop();
	}

	@Override
	public void stop() {
		super.stop();
		this.entity.setAnimation(entity.ANIMATION_IDLE);
	}

	@Override
	public boolean canUse() {
		return getTime() && !this.entity.isOnFire() && this.entity.isOnGround();
	}

	@Override
	public boolean canContinueToUse() {
		return getTime() && !this.entity.isOnFire() && this.entity.isOnGround();
	}

	private boolean getTime() {
		return this.entity.activity() == ActivityType.DIURNAL ? this.entity.level.isNight() : this.entity.activity() == ActivityType.NOCTURNAL ? this.entity.level.isDay() : false;
	}
}
