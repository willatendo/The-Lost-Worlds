package lostworlds.library.entity.goal.terrestrial;

import java.util.EnumSet;

import lostworlds.library.entity.terrestrial.EggLayingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class SleepyLookRandomlyGoal extends Goal {
	private final EggLayingEntity entity;
	private double relX;
	private double relZ;
	private int lookTime;

	public SleepyLookRandomlyGoal(EggLayingEntity entity) {
		this.entity = entity;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.entity.getRandom().nextFloat() < 0.02F && !this.entity.isSleeping()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return this.lookTime >= 0;
	}

	@Override
	public void start() {
		double d0 = (Math.PI * 2D) * this.entity.getRandom().nextDouble();
		this.relX = Math.cos(d0);
		this.relZ = Math.sin(d0);
		this.lookTime = 20 + this.entity.getRandom().nextInt(20);
	}

	@Override
	public void tick() {
		--this.lookTime;
		this.entity.getLookControl().setLookAt(this.entity.getX() + this.relX, this.entity.getEyeY(), this.entity.getZ() + this.relZ);
	}
}
