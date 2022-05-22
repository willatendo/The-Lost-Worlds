package lostworlds.server.entity.goal;

import java.util.function.Predicate;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class ReasonedAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
	public ReasonedAttackableTargetGoal(PathfinderMob entity, Class<T> target, Predicate<LivingEntity> predicate) {
		super(entity, target, 10, true, false, predicate);
	}
}
