package lostworlds.library.entity.goal.terrestrial.herbivore;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SleepyBreedGoal extends Goal 
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	protected final HerbivoreEntity entity;
	private final Class<? extends HerbivoreEntity> partnerClass;
	protected final World level;
	protected HerbivoreEntity partner;
	private int loveTime;
	private final double speedModifier;

	public SleepyBreedGoal(HerbivoreEntity entity, double speedModifier) 
	{
		this(entity, speedModifier, entity.getClass());
	}

	public SleepyBreedGoal(HerbivoreEntity entity, double speedModifier, Class<? extends HerbivoreEntity> partnerClass) 
	{
		this.entity = entity;
		this.level = entity.level;
		this.partnerClass = partnerClass;
		this.speedModifier = speedModifier;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() 
	{
		if(!this.entity.isInLove() && !this.entity.isSleeping()) 
		{
			return false;
		} 
		else 
		{
			this.partner = this.getFreePartner();
			return this.partner != null;
		}
	}

	@Override
	public boolean canContinueToUse() 
	{
		return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
	}

	@Override
	public void stop() 
	{
		this.partner = null;
		this.loveTime = 0;
	}

	@Override
	public void tick() 
	{
		this.entity.getLookControl().setLookAt(this.partner, 10.0F, (float) this.entity.getMaxHeadXRot());
		this.entity.getNavigation().moveTo(this.partner, this.speedModifier);
		++this.loveTime;
		if(this.loveTime >= 60 && this.entity.distanceToSqr(this.partner) < 9.0D) 
		{
			this.breed();
		}
	}

	@Nullable
	private HerbivoreEntity getFreePartner() 
	{
		List<HerbivoreEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.entity, this.entity.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		HerbivoreEntity entityentity = null;

		for(HerbivoreEntity entityentity1 : list) 
		{
			if(this.entity.canMate(entityentity1) && this.entity.distanceToSqr(entityentity1) < d0) 
			{
				entityentity = entityentity1;
				d0 = this.entity.distanceToSqr(entityentity1);
			}
		}

		return entityentity;
	}

	protected void breed() 
	{
		this.entity.spawnChildFromBreeding((ServerWorld) this.level, this.partner);
	}
	
	public static class Egg extends SleepyBreedGoal
	{
		private final HerbivoreEggLayingEntity entity;

		public Egg(HerbivoreEggLayingEntity entity, double speedModifier) 
		{
			super(entity, speedModifier);
			this.entity = entity;
		}

		@Override
		public boolean canUse() 
		{
			return super.canUse() && !this.entity.hasEgg();
		}

		@Override
		protected void breed() 
		{
			ServerPlayerEntity serverplayerentity = this.entity.getLoveCause();
			if(serverplayerentity == null && this.partner.getLoveCause() != null)
			{
				serverplayerentity = this.partner.getLoveCause();
			}

			if(serverplayerentity != null) 
			{
				serverplayerentity.awardStat(Stats.ANIMALS_BRED);
			}

			this.entity.setHasEgg(true);
			this.entity.resetLove();
			this.partner.resetLove();
			Random random = this.entity.getRandom();
			if(this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) 
			{
				this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), random.nextInt(7) + 1));
			}
		}
	}
}
