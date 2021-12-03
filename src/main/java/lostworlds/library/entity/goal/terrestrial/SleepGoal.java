package lostworlds.library.entity.goal.terrestrial;

import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import net.minecraft.entity.ai.goal.Goal;

public class SleepGoal extends Goal
{
	private final PrehistoricEntity entity;
	
	public SleepGoal(PrehistoricEntity entity) 
	{
		this.entity = entity;
	}

	@Override
	public void tick() 
	{
		super.tick();
		this.entity.setAnimation(entity.ANIMATION_SLEEP);
		this.entity.getNavigation().stop();
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.entity.setAnimation(entity.ANIMATION_IDLE);
	}
	
	@Override
	public boolean canUse() 
	{
		return this.entity.level.isNight() && !this.entity.isOnFire() && this.entity.isOnGround();
	}
	
	@Override
	public boolean canContinueToUse() 
	{
		return this.entity.level.isNight() && !this.entity.isOnFire() && this.entity.isOnGround();
	}
}
