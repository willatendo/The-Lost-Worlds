package lostworlds.server.entity.goal.terrestrial;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

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
		} else if (this.entity instanceof NeutralMob) {
			if (((NeutralMob) this.entity).isAngry()) {
				return super.canUse();
			}
		} else {
			return false;
		}
		return false;
	}
}
