package lostworlds.library.entity.goal.carnivore;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SleepyBreedGoal extends Goal 
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	protected final CarnivoreEntity entity;
	private final Class<? extends CarnivoreEntity> partnerClass;
	protected final World level;
	protected CarnivoreEntity partner;
	private int loveTime;
	private final double speedModifier;

	public SleepyBreedGoal(CarnivoreEntity entity, double speedModifier) 
	{
		this(entity, speedModifier, entity.getClass());
	}

	public SleepyBreedGoal(CarnivoreEntity entity, double speedModifier, Class<? extends CarnivoreEntity> partnerClass) 
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
		if(!this.entity.isInLove() || this.entity.isSleeping()) 
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
	private CarnivoreEntity getFreePartner() 
	{
		List<CarnivoreEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.entity, this.entity.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		CarnivoreEntity entityentity = null;

		for(CarnivoreEntity entityentity1 : list) 
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
}
