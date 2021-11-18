package lostworlds.library.entity.goal;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class NaturalBreedingGoal extends Goal 
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	protected final PrehistoricEntity entity;
	private final Class<? extends PrehistoricEntity> partnerClass;
	protected final World level;
	protected PrehistoricEntity partner;
	private int naturalLoveTime;
	private final double speedModifier;

	public NaturalBreedingGoal(PrehistoricEntity entity, double speedModifier) 
	{
		this(entity, speedModifier, entity.getClass());
	}

	public NaturalBreedingGoal(PrehistoricEntity entity, double speedModifier, Class<? extends PrehistoricEntity> partnerClass) 
	{
		this.entity = entity;
		this.level = entity.level;
		this.partnerClass = partnerClass;
		this.speedModifier = speedModifier;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public boolean canUse() 
	{
		if(!this.entity.isInNaturalLove() || this.entity.isSleeping()) 
		{
			return false;
		} 
		else 
		{
			this.partner = this.getFreePartner();
			return this.partner != null;
		}
	}

	public boolean canContinueToUse() 
	{
		return this.partner.isAlive() && this.partner.isInNaturalLove() && !this.partner.isBaby() && !this.entity.isBaby() && this.naturalLoveTime < 60;
	}

	public void stop() 
	{
		this.partner = null;
		this.naturalLoveTime = 0;
	}

	public void tick() 
	{
		this.entity.getLookControl().setLookAt(this.partner, 10.0F, (float) this.entity.getMaxHeadXRot());
		this.entity.getNavigation().moveTo(this.partner, this.speedModifier);
		++this.naturalLoveTime;
		if(this.naturalLoveTime >= 60 && this.entity.distanceToSqr(this.partner) < 9.0D) 
		{
			this.breed();
		}
	}

	@Nullable
	private PrehistoricEntity getFreePartner() 
	{
		List<PrehistoricEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.entity, this.entity.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		PrehistoricEntity prehistoric = null;	

		for(PrehistoricEntity prehistoric1 : list) 
		{
			if(this.entity.canMate(prehistoric1) && this.entity.distanceToSqr(prehistoric1) < d0) 
			{
				prehistoric = prehistoric1;
				d0 = this.entity.distanceToSqr(prehistoric1);
			}
		}

		return prehistoric;
	}

	protected void breed() 
	{
		this.entity.spawnChildFromNaturalBreeding((ServerWorld) this.level, this.partner);
	}
	
	public static class Egg extends NaturalBreedingGoal
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
			this.entity.resetNaturalLove();
			this.partner.resetNaturalLove();
			Random random = this.entity.getRandom();
			if(this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) 
			{
				this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), random.nextInt(7) + 1));
			}
		}
	}
}
