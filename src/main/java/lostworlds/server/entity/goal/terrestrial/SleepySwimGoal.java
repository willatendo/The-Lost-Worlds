package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

public class SleepySwimGoal extends Goal {
	private final EggLayingMob entity;

	public SleepySwimGoal(EggLayingMob entity) {
		this.entity = entity;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP));
		entity.getNavigation().setCanFloat(true);
	}

	@Override
	public boolean canUse() {
		return this.entity.isInWater() && this.entity.getFluidHeight(FluidTags.WATER) > this.entity.getFluidJumpThreshold() && !this.entity.isSleeping() || this.entity.isInLava() && !this.entity.isSleeping();
	}

	@Override
	public void tick() {
		if (this.entity.getRandom().nextFloat() < 0.8F) {
			this.entity.getJumpControl().jump();
		}
	}
}
