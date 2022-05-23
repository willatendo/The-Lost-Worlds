package lostworlds.server.entity.goal.terrestrial;

import javax.annotation.Nullable;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

public class SleepyWaterAvoidingRandomStrollGoal extends SleepyRandomStrollGoal {
	protected final float probability;

	public SleepyWaterAvoidingRandomStrollGoal(EggLayingMob entity, double speedModifier) {
		this(entity, speedModifier, 0.001F);
	}

	public SleepyWaterAvoidingRandomStrollGoal(EggLayingMob entity, double speedModifier, float probability) {
		super(entity, speedModifier);
		this.probability = probability;
	}

	@Override
	@Nullable
	protected Vec3 getPosition() {
		if (this.entity.isInWaterOrBubble()) {
			Vec3 vec3 = LandRandomPos.getPos(this.entity, 15, 7);
			return vec3 == null ? super.getPosition() : vec3;
		} else {
			return this.entity.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.entity, 10, 7) : super.getPosition();
		}
	}

	public static class Egg extends SleepyWaterAvoidingRandomStrollGoal {
		private final EggLayingMob entity;

		public Egg(EggLayingMob entity, double speedModifier) {
			super(entity, speedModifier);
			this.entity = entity;
		}

		@Override
		public boolean canUse() {
			return !this.entity.isGoingHome() && !this.entity.hasEgg() ? super.canUse() : false;
		}
	}
}
