package lostworlds.library.entity.utils;

import java.util.function.Predicate;

import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public interface IReasonableAngerable extends IAngerable
{
	default boolean isHungry(LivingEntity entity)
	{
		if(entity instanceof PrehistoricEntity)
		{
			if(((PrehistoricEntity) entity).isHungry())
			{
				return true;
			}
		}
		else
		{
			return false;
		}
		return false;
	}
	
	public class ReasonedAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
	{
		public ReasonedAttackableTargetGoal(MobEntity entity, Class targetClass, Predicate<LivingEntity> reason) 
		{
			super(entity, targetClass, 10, true, false, reason);
		}
	}
}
