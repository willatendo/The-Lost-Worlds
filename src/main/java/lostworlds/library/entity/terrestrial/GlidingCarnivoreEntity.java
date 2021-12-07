package lostworlds.library.entity.terrestrial;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class GlidingCarnivoreEntity extends CarnivoreEntity
{
	public float flap;
	public float flapSpeed;
	public float flapping = 1.0F;
	
	public GlidingCarnivoreEntity(EntityType<? extends CarnivoreEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	@Override
	public void aiStep()
	{
		super.aiStep();
		if(this.isClimbing() && !this.isSleeping())
		{
			this.setAnimation(ANIMATION_WALL_WALK);
		}
		else if(!this.isOnGround() && !this.isClimbing() && !this.isSleeping())
		{
			this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
			this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
			if(!this.onGround && this.flapping < 1.0F) 
			{
				this.flapping = 1.0F;
				this.setAnimation(ANIMATION_GLIDE);
			}

			this.flapping = (float) ((double) this.flapping * 0.9D);
			Vector3d vector3d = this.getDeltaMovement();
			if(!this.onGround && vector3d.y < 0.0D) 
			{
				this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
			}

			this.flap += this.flapping * 2.0F;
		}
		else if(this.getAnimation() != ANIMATION_EAT && this.getAnimation() != ANIMATION_SLEEP)
		{
			this.setAnimation(ANIMATION_IDLE);
		}
	}
	
	@Override
	public boolean onClimbable() 
	{
		return this.isClimbing() && !this.isSleeping();
	}
	
	public boolean isClimbing() 
	{
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setClimbing(boolean climbing) 
	{
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if(climbing) 
		{
			b0 = (byte) (b0 | 1);
		} 
		else 
		{
			b0 = (byte) (b0 & -2);
		}
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(!this.level.isClientSide) 
		{
			this.setClimbing(this.horizontalCollision);
		}
	}

	@Override
	public boolean causeFallDamage(float f1, float f2) 
	{
		return false;
	}
}
