package lostworlds.library.entity.goal.terrestrial.carnivore;

import lostworlds.library.entity.Size;
import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;

public class CarnivoreHuntGoal extends Goal
{
	private final double speedModifier;
	private Path path;
	private double pathedTargetX;
	private double pathedTargetY;
	private double pathedTargetZ;
	private int ticksUntilNextPathRecalculation;
	private int ticksUntilNextAttack;
	private long lastCanUseCheck;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;
	private final CarnivoreEntity entity;
	private final Size entitySize;
	
	public CarnivoreHuntGoal(CarnivoreEntity entity, Size entitySize) 
	{
		this.entity = entity;
		this.entitySize = entitySize;
		this.speedModifier = 1.0D;
	}

	@Override
	public boolean canUse() 
	{
		long i = this.entity.level.getGameTime();
		if(!(i - this.lastCanUseCheck < 20L) && this.entity.isHungry() && entity.getSize().equalToSmallerThan(entitySize))
		{
			return true;
		}
		else
		{
			this.lastCanUseCheck = i;
			LivingEntity target = this.entity.getTarget();
			if(target == null) 
			{
				return false;
			} 
			else if(!target.isAlive()) 
			{
				return false;
			} 
			else 
			{
				if(canPenalize) 
				{
					if(--this.ticksUntilNextPathRecalculation <= 0) 
					{
						this.path = this.entity.getNavigation().createPath(target, 0);
						this.ticksUntilNextPathRecalculation = 4 + this.entity.getRandom().nextInt(7);
						return this.path != null;
					} 
					else 
					{
						return true;					
					}
				}
				this.path = this.entity.getNavigation().createPath(target, 0);
				if(this.path != null) 
				{
					return true;
				}
				else 
				{
					return this.getAttackReachSqr(target) >= this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
				}
			}
		}
	}

	@Override
	public boolean canContinueToUse() 
	{
		LivingEntity target = this.entity.getTarget();
		if(target == null) 
		{
			return false;
		} 
		else if(!target.isAlive()) 
		{
			return false;
		} 
		else if(!this.entity.isWithinRestriction(target.blockPosition())) 
		{
			return false;
		} 
		else 
		{
			return !(target instanceof PlayerEntity) || !target.isSpectator() && !((PlayerEntity) target).isCreative();
		}
	}

	@Override
	public void start() 
	{
		this.entity.getNavigation().moveTo(this.path, this.speedModifier);
		this.entity.setAggressive(true);
		this.ticksUntilNextPathRecalculation = 0;
		this.ticksUntilNextAttack = 0;
	}

	@Override
	public void stop() 
	{
		LivingEntity target = this.entity.getTarget();
		if(!EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(target)) 
		{
			this.entity.setTarget((LivingEntity) null);
		}

		this.entity.setAggressive(false);
		this.entity.getNavigation().stop();
	}

	@Override
	public void tick() 
	{
		LivingEntity target = this.entity.getTarget();
		this.entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
		double distance = this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
		this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
		if((this.entity.getSensing().canSee(target)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || target.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.entity.getRandom().nextFloat() < 0.05F)) 
		{
			this.pathedTargetX = target.getX();
			this.pathedTargetY = target.getY();
			this.pathedTargetZ = target.getZ();
			this.ticksUntilNextPathRecalculation = 4 + this.entity.getRandom().nextInt(7);
			if(this.canPenalize) 
			{
				this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
				if(this.entity.getNavigation().getPath() != null) 
				{
					PathPoint finalPathPoint = this.entity.getNavigation().getPath().getEndNode();
					if(finalPathPoint != null && target.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
					{
						failedPathFindingPenalty = 0;
					}
					else
					{
						failedPathFindingPenalty += 10;
					}
				} 
				else 
				{
					failedPathFindingPenalty += 10;
				}
			}
			if(distance > 1024.0D) 
			{
				this.ticksUntilNextPathRecalculation += 10;
			} 
			else if(distance > 256.0D) 
			{
				this.ticksUntilNextPathRecalculation += 5;
			}

			if(!this.entity.getNavigation().moveTo(target, this.speedModifier)) 
			{
				this.ticksUntilNextPathRecalculation += 15;
			}
		}

		this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
		this.checkAndPerformAttack(target, distance);
	}

	protected void checkAndPerformAttack(LivingEntity entity, double attack) 
	{
		double distance = this.getAttackReachSqr(entity);
		if(attack <= distance && this.ticksUntilNextAttack <= 0) 
		{
			this.resetAttackCooldown();
			this.entity.swing(Hand.MAIN_HAND);
			this.entity.doHurtTarget(entity);
		}

	}

	protected void resetAttackCooldown() 
	{
		this.ticksUntilNextAttack = 20;
	}

	protected boolean isTimeToAttack() 
	{
		return this.ticksUntilNextAttack <= 0;
	}

	protected int getTicksUntilNextAttack() 
	{
		return this.ticksUntilNextAttack;
	}

	protected int getAttackInterval() 
	{
		return 20;
	}

	protected double getAttackReachSqr(LivingEntity entity) 
	{
		return (double) (this.entity.getBbWidth() * 2.0F * this.entity.getBbWidth() * 2.0F + entity.getBbWidth());
	}
}
