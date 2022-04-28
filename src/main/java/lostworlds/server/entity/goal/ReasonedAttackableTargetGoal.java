package lostworlds.server.entity.goal;

import java.util.function.Predicate;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class ReasonedAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
	public ReasonedAttackableTargetGoal(CreatureEntity entity, Class<T> target, Predicate<LivingEntity> predicate) {
		super(entity, target, 10, true, false, predicate);
	}
}
