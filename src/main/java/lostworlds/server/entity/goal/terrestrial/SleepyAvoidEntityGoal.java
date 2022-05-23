package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;
import java.util.function.Predicate;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class SleepyAvoidEntityGoal<T extends LivingEntity> extends Goal {
	protected final EggLayingMob entity;
	private final double walkSpeedModifier;
	private final double sprintSpeedModifier;
	protected T toAvoid;
	protected final float maxDist;
	protected Path path;
	protected final PathNavigation pathNav;
	protected final Class<T> avoidClass;
	protected final Predicate<LivingEntity> avoidPredicate;
	protected final Predicate<LivingEntity> predicateOnAvoidEntity;
	private final TargetingConditions avoidEntityTargeting;

	public SleepyAvoidEntityGoal(EggLayingMob entity, Class<T> avoidEntity, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
		this(entity, avoidEntity, (p_200828_0_) -> {
			return true;
		}, maxDist, walkSpeedModifier, sprintSpeedModifier, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
	}

	public SleepyAvoidEntityGoal(EggLayingMob entity, Class<T> avoidEntity, Predicate<LivingEntity> avoidPredicate, float maxDist, double walkSpeedModifier, double sprintSpeedModifier, Predicate<LivingEntity> predicateOnAvoidEntity) {
		this.entity = entity;
		this.avoidClass = avoidEntity;
		this.avoidPredicate = avoidPredicate;
		this.maxDist = maxDist;
		this.walkSpeedModifier = walkSpeedModifier;
		this.sprintSpeedModifier = sprintSpeedModifier;
		this.predicateOnAvoidEntity = predicateOnAvoidEntity;
		this.pathNav = entity.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.avoidEntityTargeting = TargetingConditions.forCombat().range((double) maxDist).selector(predicateOnAvoidEntity.and(avoidPredicate));
	}

	public SleepyAvoidEntityGoal(EggLayingMob entity, Class<T> avoidEntity, float maxDist, double walkSpeedModifier, double sprintSpeedModifier, Predicate<LivingEntity> predicateOnAvoidEntity) {
		this(entity, avoidEntity, (p_203782_0_) -> {
			return true;
		}, maxDist, walkSpeedModifier, sprintSpeedModifier, predicateOnAvoidEntity);
	}

	@Override
	public boolean canUse() {
		this.toAvoid = this.entity.level.getNearestEntity(this.avoidClass, this.avoidEntityTargeting, this.entity, this.entity.getX(), this.entity.getY(), this.entity.getZ(), this.entity.getBoundingBox().inflate((double) this.maxDist, 3.0D, (double) this.maxDist));
		if (this.toAvoid == null) {
			return false;
		} else if (this.entity.isSleeping()) {
			return false;
		} else {
			Vec3 vector3d = DefaultRandomPos.getPosAway(this.entity, 16, 7, this.toAvoid.position());
			if (vector3d == null) {
				return false;
			} else if (this.toAvoid.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < this.toAvoid.distanceToSqr(this.entity)) {
				return false;
			} else {
				this.path = this.pathNav.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
				return this.path != null;
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !this.pathNav.isDone();
	}

	@Override
	public void start() {
		this.pathNav.moveTo(this.path, this.walkSpeedModifier);
	}

	@Override
	public void stop() {
		this.toAvoid = null;
	}

	@Override
	public void tick() {
		if (this.entity.distanceToSqr(this.toAvoid) < 49.0D) {
			this.entity.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
		} else {
			this.entity.getNavigation().setSpeedModifier(this.walkSpeedModifier);
		}
	}
}
