package lostworlds.library.entity.prehistoric;

import lostworlds.library.entity.TimeEras;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AquaticPrehistoricEntity extends PrehistoricEntity
{
	public AquaticPrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world, era);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 40));
	}
	
	@Override
	public boolean canBreatheUnderwater() 
	{
		return true;
	}
	
	@Override	
	public CreatureAttribute getMobType() 
	{
		return CreatureAttribute.WATER;
	}
	
	@Override
	public int getAmbientSoundInterval() 
	{
		return 120;
	}
	
	protected void handleAirSupply(int air) 
	{
		if(this.isAlive() && !this.isInWaterOrBubble()) 
		{
			this.setAirSupply(air - 1);
			if(this.getAirSupply() == -20) 
			{
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		} 
		else 
		{
			this.setAirSupply(300);
		}
	}
	
	@Override
	public void baseTick() 
	{
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}
	
	@Override
	public boolean isPushedByFluid() 
	{
		return false;
	}
	
	@Override
	public boolean canBeLeashed(PlayerEntity player) 
	{
		return false;
	}
	
	@Override
	public void travel(Vector3d vec3d) 
	{
		if(this.isEffectiveAi() && this.isInWater()) 
		{
			this.moveRelative(0.01F, vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if(this.getTarget() == null) 
			{
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} 
		else 
		{
			super.travel(vec3d);
		}
	}
	
	@Override
	public void aiStep() 
	{
		if(!this.isInWater() && this.onGround && this.verticalCollision) 
		{
			this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.onGround = false;
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}
		super.aiStep();
	}
	
	protected abstract SoundEvent getFlopSound();
}
