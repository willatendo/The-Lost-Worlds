package lostworlds.library.entity.goal.carnivore;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.goal.Goal;

public class CarnivoreHuntGoal extends Goal
{
	private final CarnivoreEntity entity;
	
	public CarnivoreHuntGoal(CarnivoreEntity entity) 
	{
		this.entity = entity;
	}

	@Override
	public boolean canUse() 
	{
		if(!this.entity.isHungry())
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
		if(this.entity.isHungry())
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
