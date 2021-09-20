package lostworlds.library.entity.goal.herbivore;

import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class SleepGoal extends Goal
{
	private final HerbivoreEntity entity;
	private final World world;
	
	public SleepGoal(HerbivoreEntity entity) 
	{
		this.entity = entity;
		this.world = entity.level;
	}

	@Override
	public boolean canUse() 
	{
		if(this.world.isNight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean canContinueToUse() 
	{
		if(!(this.entity.isPanicked()) && this.world.isNight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.entity.setSleeping(false);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.entity.setSleeping(true);
	}
}
