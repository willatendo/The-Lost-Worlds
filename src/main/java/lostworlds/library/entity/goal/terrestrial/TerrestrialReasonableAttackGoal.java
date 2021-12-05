package lostworlds.library.entity.goal.terrestrial;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class TerrestrialReasonableAttackGoal extends MeleeAttackGoal
{
	private final CarnivoreEntity entity;
	
	public TerrestrialReasonableAttackGoal(CarnivoreEntity entity, double speedModifier) 
	{
		super(entity, speedModifier, false);
		this.entity = entity;
	}

	@Override
	public boolean canUse() 
	{
		return this.entity.isHungry() ? super.canUse() : false;
	}
}
