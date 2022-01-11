package lostworlds.library.entity.goal.terrestrial;

import lostworlds.library.entity.terrestrial.EggLayingEntity;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class TerrestrialReasonableAttackGoal extends MeleeAttackGoal {
	private final EggLayingEntity entity;

	public TerrestrialReasonableAttackGoal(EggLayingEntity entity, double speedModifier) {
		super(entity, speedModifier, false);
		this.entity = entity;
	}

	@Override
	public boolean canUse() {
		if (this.entity.isHungry()) {
			return super.canUse();
		} else if (this.entity instanceof IAngerable) {
			if (((IAngerable) this.entity).isAngry()) {
				return super.canUse();
			}
		} else {
			return false;
		}
		return false;
	}
}
