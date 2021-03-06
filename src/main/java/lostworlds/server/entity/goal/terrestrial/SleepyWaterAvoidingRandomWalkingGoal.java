package lostworlds.server.entity.goal.terrestrial;

import javax.annotation.Nullable;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.vector.Vector3d;

public class SleepyWaterAvoidingRandomWalkingGoal extends SleepyRandomWalkingGoal {
	protected final float probability;

	public SleepyWaterAvoidingRandomWalkingGoal(EggLayingEntity entity, double speedModifier) {
		this(entity, speedModifier, 0.001F);
	}

	public SleepyWaterAvoidingRandomWalkingGoal(EggLayingEntity entity, double speedModifier, float probability) {
		super(entity, speedModifier);
		this.probability = probability;
	}

	@Override
	@Nullable
	protected Vector3d getPosition() {
		if (this.entity.isInWaterOrBubble()) {
			Vector3d vector3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
			return vector3d == null ? super.getPosition() : vector3d;
		} else {
			return this.entity.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
		}
	}

	public static class Egg extends SleepyWaterAvoidingRandomWalkingGoal {
		private final EggLayingEntity entity;

		public Egg(EggLayingEntity entity, double speedModifier) {
			super(entity, speedModifier);
			this.entity = entity;
		}

		@Override
		public boolean canUse() {
			return !this.entity.isGoingHome() && !this.entity.hasEgg() ? super.canUse() : false;
		}
	}
}
