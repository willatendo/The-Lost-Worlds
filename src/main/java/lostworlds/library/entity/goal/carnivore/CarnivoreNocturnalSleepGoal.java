package lostworlds.library.entity.goal.carnivore;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class CarnivoreNocturnalSleepGoal extends Goal
{
	private final CarnivoreEntity entity;
	private final World world;
	
	public CarnivoreNocturnalSleepGoal(CarnivoreEntity entity) 
	{
		this.entity = entity;
		this.world = entity.level;
	}

	@Override
	public boolean canUse() 
	{
		if(this.world.isDay())
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
		if(this.world.isDay())
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
