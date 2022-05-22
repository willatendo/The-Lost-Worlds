package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class SleepyBreedGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = (new TargetingConditions()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	protected final EggLayingEntity entity;
	private final Class<? extends EggLayingEntity> partnerClass;
	protected final Level level;
	protected EggLayingEntity partner;
	private int loveTime;
	private final double speedModifier;

	public SleepyBreedGoal(EggLayingEntity entity, double speedModifier) {
		this(entity, speedModifier, entity.getClass());
	}

	public SleepyBreedGoal(EggLayingEntity entity, double speedModifier, Class<? extends EggLayingEntity> partnerClass) {
		this.entity = entity;
		this.level = entity.level;
		this.partnerClass = partnerClass;
		this.speedModifier = speedModifier;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.entity.isInLove() || this.entity.isSleeping()) {
			return false;
		} else {
			this.partner = this.getFreePartner();
			return this.partner != null;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
	}

	@Override
	public void stop() {
		this.partner = null;
		this.loveTime = 0;
	}

	@Override
	public void tick() {
		this.entity.getLookControl().setLookAt(this.partner, 10.0F, (float) this.entity.getMaxHeadXRot());
		this.entity.getNavigation().moveTo(this.partner, this.speedModifier);
		++this.loveTime;
		if (this.loveTime >= 60 && this.entity.distanceToSqr(this.partner) < 9.0D) {
			this.breed();
		}
	}

	@Nullable
	private EggLayingEntity getFreePartner() {
		List<EggLayingEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.entity, this.entity.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		EggLayingEntity entityentity = null;

		for (EggLayingEntity entityentity1 : list) {
			if (this.entity.canMate(entityentity1) && this.entity.distanceToSqr(entityentity1) < d0) {
				entityentity = entityentity1;
				d0 = this.entity.distanceToSqr(entityentity1);
			}
		}

		return entityentity;
	}

	protected void breed() {
		this.entity.spawnChildFromBreeding((ServerLevel) this.level, this.partner);
	}

	public static class Egg extends SleepyBreedGoal {
		private final EggLayingEntity entity;

		public Egg(EggLayingEntity entity, double speedModifier) {
			super(entity, speedModifier);
			this.entity = entity;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && !this.entity.hasEgg();
		}

		@Override
		protected void breed() {
			ServerPlayer serverplayerentity = this.entity.getLoveCause();
			if (serverplayerentity == null && this.partner.getLoveCause() != null) {
				serverplayerentity = this.partner.getLoveCause();
			}

			if (serverplayerentity != null) {
				serverplayerentity.awardStat(Stats.ANIMALS_BRED);
			}

			this.entity.setHasEgg(true);
			this.entity.resetLove();
			this.partner.resetLove();
			Random random = this.entity.getRandom();
			if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
				this.level.addFreshEntity(new ExperienceOrb(this.level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), random.nextInt(7) + 1));
			}
		}
	}
}
