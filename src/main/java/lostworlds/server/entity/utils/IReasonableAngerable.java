package lostworlds.server.entity.utils;

import java.util.function.Predicate;

import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public interface IReasonableAngerable extends IAngerable {
	
	//To Move To New Class
	public class ReasonedAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public ReasonedAttackableTargetGoal(MobEntity entity, Class targetClass, Predicate<LivingEntity> reason) {
			super(entity, targetClass, 10, true, false, reason);
		}
	}
}
