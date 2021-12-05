package lostworlds.library.entity.goal.terrestrial;

import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import lostworlds.library.entity.utils.enums.ActivityType;
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
		return getTime() && !this.entity.isOnFire() && this.entity.isOnGround();
	}
	
	@Override
	public boolean canContinueToUse() 
	{
		return getTime() && !this.entity.isOnFire() && this.entity.isOnGround();
	}
	
	private boolean getTime()
	{
		return this.entity.activity() == ActivityType.DIURNAL ? this.entity.level.isNight() : this.entity.activity() == ActivityType.NOCTURNAL ? this.entity.level.isDay() : false;
	}
}
