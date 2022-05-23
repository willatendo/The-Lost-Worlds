package lostworlds.server.entity.goal.terrestrial;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class TerrestrialReasonableAttackGoal extends MeleeAttackGoal {
	private final EggLayingMob entity;

	public TerrestrialReasonableAttackGoal(EggLayingMob entity, double speedModifier) {
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
