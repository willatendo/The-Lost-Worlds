package lostworlds.library.entity.goal.herbivore;

import java.util.EnumSet;

import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.entity.ai.goal.Goal;

public class ScratchGoal extends Goal 
{
	private static final int ANIMATION_LENGTH = 32;
	private final HerbivoreEntity entity;
	private int cleanTime;
	private int nextCleanTime;

	public ScratchGoal(HerbivoreEntity entity) 
	{
		this.entity = entity;
		this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
		nextCleanTime = entity.age + (10 * 20 + entity.getRandom().nextInt(10) * 20);
	}

	@Override
	public boolean canUse() 
	{
		if(nextCleanTime > entity.age || entity.isScratching() == false) 
		{
			return false;
		}
		return entity.getRandom().nextInt(40) == 0;
	}

	@Override
	public void start() 
	{
		cleanTime = ANIMATION_LENGTH;
		entity.setScratching(true);
		nextCleanTime = entity.age + (10 * 20 + entity.getRandom().nextInt(10) * 20);
	}

	@Override
	public void stop() 
	{
		entity.setScratching(false);
	}

	@Override
	public boolean canContinueToUse() 
	{
		return cleanTime >= 0;
	}

	@Override
	public void tick() 
	{
		cleanTime--;
	}
}
