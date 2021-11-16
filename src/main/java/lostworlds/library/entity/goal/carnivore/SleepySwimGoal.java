package lostworlds.library.entity.goal.carnivore;

import java.util.EnumSet;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

public class SleepySwimGoal extends Goal 
{
	private final CarnivoreEntity entity;

	public SleepySwimGoal(CarnivoreEntity entity) 
	{
		this.entity = entity;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP));
		entity.getNavigation().setCanFloat(true);
	}
	
	@Override
	public boolean canUse() 
	{
		return this.entity.isInWater() && this.entity.getFluidHeight(FluidTags.WATER) > this.entity.getFluidJumpThreshold() && !this.entity.isSleeping() || this.entity.isInLava() && !this.entity.isSleeping();
	}

	@Override
	public void tick() 
	{
		if(this.entity.getRandom().nextFloat() < 0.8F) 
		{
			this.entity.getJumpControl().jump();
		}
	}
}
