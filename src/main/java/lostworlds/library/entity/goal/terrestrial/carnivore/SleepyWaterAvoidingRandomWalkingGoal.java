package lostworlds.library.entity.goal.terrestrial.carnivore;

import javax.annotation.Nullable;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.vector.Vector3d;

public class SleepyWaterAvoidingRandomWalkingGoal extends SleepyRandomWalkingGoal
{
	protected final float probability;
	
	public SleepyWaterAvoidingRandomWalkingGoal(CarnivoreEntity entity, double speedModifier) 
	{
		this(entity, speedModifier, 0.001F);
	}

	public SleepyWaterAvoidingRandomWalkingGoal(CarnivoreEntity entity, double speedModifier, float probability) 
	{
		super(entity, speedModifier);
		this.probability = probability;
	}
	
	@Override
	@Nullable
	protected Vector3d getPosition() 
	{
		if(this.entity.isInWaterOrBubble()) 
		{
			Vector3d vector3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
			return vector3d == null ? super.getPosition() : vector3d;
		} 
		else 
		{
			return this.entity.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
		}
	}
}
