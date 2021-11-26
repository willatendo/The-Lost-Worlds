package lostworlds.library.entity.goal.terrestrial.herbivore;

import java.util.EnumSet;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

public class SleepySwimGoal extends Goal 
{
	private final HerbivoreEntity entity;

	public SleepySwimGoal(HerbivoreEntity entity) 
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
	
	public static class Egg extends SleepySwimGoal
	{
		private final HerbivoreEggLayingEntity entity;
		
		public Egg(HerbivoreEggLayingEntity entity) 
		{
			super(entity);
			this.entity = entity;
		}
		
		@Override
		public boolean canUse() 
		{
			return !this.entity.isGoingHome() && !this.entity.hasEgg() ? super.canUse() : false;
		}
	}
}
