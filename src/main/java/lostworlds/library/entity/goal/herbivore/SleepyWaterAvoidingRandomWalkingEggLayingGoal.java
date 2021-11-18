package lostworlds.library.entity.goal.herbivore;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;

public class SleepyWaterAvoidingRandomWalkingEggLayingGoal extends SleepyWaterAvoidingRandomWalkingGoal 
{
	private final HerbivoreEggLayingEntity entity;
	
	public SleepyWaterAvoidingRandomWalkingEggLayingGoal(HerbivoreEggLayingEntity entity, double speedModifier) 
	{
		super(entity, speedModifier);
		this.entity = entity;
	}
	
	@Override
	public boolean canUse() 
	{
		return !this.entity.isGoingHome() && !this.entity.hasEgg() ? super.canUse() : false;
	}
}
