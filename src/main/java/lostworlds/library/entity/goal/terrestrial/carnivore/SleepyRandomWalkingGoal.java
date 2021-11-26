package lostworlds.library.entity.goal.terrestrial.carnivore;

import java.util.EnumSet;

import javax.annotation.Nullable;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

public class SleepyRandomWalkingGoal extends Goal 
{
	protected final CarnivoreEntity entity;
	protected double wantedX;
	protected double wantedY;
	protected double wantedZ;
	protected final double speedModifier;
	protected int interval;
	protected boolean forceTrigger;
	private boolean checkNoActionTime;

	public SleepyRandomWalkingGoal(CarnivoreEntity entity, double speedModifier) 
	{
		this(entity, speedModifier, 120);
	}

	public SleepyRandomWalkingGoal(CarnivoreEntity entity, double speedModifier, int interval) {
		this(entity, speedModifier, interval, true);
	}

	public SleepyRandomWalkingGoal(CarnivoreEntity entity, double speedModifier, int interval, boolean checkNoActionTime) 
	{
		this.entity = entity;
		this.speedModifier = speedModifier;
		this.interval = interval;
		this.checkNoActionTime = checkNoActionTime;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() 
	{
		if(this.entity.isVehicle() || this.entity.isSleeping()) 
		{
			return false;
		} 
		else 
		{
			if(!this.forceTrigger) 
			{
				if(this.checkNoActionTime && this.entity.getNoActionTime() >= 100) 
				{
					return false;
				}

				if(this.entity.getRandom().nextInt(this.interval) != 0) 
				{
					return false;
				}
			}

			Vector3d vector3d = this.getPosition();
			if(vector3d == null) 
			{
				return false;
			} 
			else 
			{
				this.wantedX = vector3d.x;
				this.wantedY = vector3d.y;
				this.wantedZ = vector3d.z;
				this.forceTrigger = false;
				return true;
			}
		}
	}

	@Nullable
	protected Vector3d getPosition() 
	{
		return RandomPositionGenerator.getPos(this.entity, 10, 7);
	}

	@Override
	public boolean canContinueToUse() 
	{
		return !this.entity.getNavigation().isDone() && !this.entity.isVehicle();
	}

	@Override
	public void start() 
	{
		this.entity.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}

	@Override
	public void stop() 
	{
		this.entity.getNavigation().stop();
		super.stop();
	}

	public void trigger() 
	{
		this.forceTrigger = true;
	}

	public void setInterval(int interval) 
	{
		this.interval = interval;
	}
}
