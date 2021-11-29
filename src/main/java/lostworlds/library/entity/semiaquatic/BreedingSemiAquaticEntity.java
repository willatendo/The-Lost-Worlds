package lostworlds.library.entity.semiaquatic;

import lostworlds.library.entity.controller.SemiAquaticSwimSinkMoveController;
import lostworlds.library.entity.navigators.SemiAquaticPathNavigator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class BreedingSemiAquaticEntity extends AnimalEntity implements ISemiAquatic
{    
	private boolean isLandNavigator;
	
    public BreedingSemiAquaticEntity(EntityType<? extends BreedingSemiAquaticEntity> entity, World world) 
	{
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.setPathfindingMalus(PathNodeType.WATER_BORDER, 0.0F);
		switchNavigator(false);
	}
	
	private void switchNavigator(boolean onLand) {
		if (onLand) {
			this.moveControl = new MovementController(this);
			this.navigation = new GroundPathNavigator(this, this.level);
			this.isLandNavigator = true;
		} else {
			this.moveControl = new SemiAquaticSwimSinkMoveController(this, 1.2F, 1.6F);
			this.navigation = new SemiAquaticPathNavigator(this, this.level);
			this.isLandNavigator = false;
		}
	}
	
	@Override
	public boolean shouldEnterWater() 
	{
		return true;
	}

	@Override
	public boolean shouldLeaveWater() 
	{
		return false;
	}

	@Override
	public boolean shouldStopMoving() 
	{
		return this.random.nextInt(10000) == 0;
	}

	@Override
	public int getWaterSearchRange() 
	{
		return 10;
	}
	
	@Override
	public boolean canBreatheUnderwater() 
	{
		return false;
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficuilty, SpawnReason reason, ILivingEntityData data, CompoundNBT nbt) 
	{
		this.setAirSupply(this.getMaxAirSupply());
		return super.finalizeSpawn(world, difficuilty, reason, data, nbt);
	}
	
	@Override
	public int getMaxAirSupply() 
	{
		return 4800;
	}
	
	@Override
	protected int increaseAirSupply(int air) 
	{
		return this.getMaxAirSupply();
	}
	
	@Override
	public void tick() 
	{
		if(this.isNoAi()) 
		{
			this.setAirSupply(this.getMaxAirSupply());
		}
		if(this.isInWaterOrBubble() && this.isLandNavigator) 
		{
			switchNavigator(false);
		}
		if(!this.isInWaterOrBubble() && !this.isLandNavigator) 
		{
			switchNavigator(true);
		}
		
		super.tick();
	}
	
	@Override
	public void travel(Vector3d vec3d) 
	{
		if(this.isEffectiveAi() && this.isInWater()) 
		{
			this.moveRelative(this.getSpeed(), vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			if(this.jumping) 
			{
				this.setDeltaMovement(this.getDeltaMovement().scale(1D));
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.72D, 0.0D));
			} 
			else 
			{
				this.setDeltaMovement(this.getDeltaMovement().scale(0.4D));
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
			}

		} 
		else 
		{
			super.travel(vec3d);

		}
	}
}
