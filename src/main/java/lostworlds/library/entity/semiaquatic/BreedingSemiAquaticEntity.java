package lostworlds.library.entity.semiaquatic;

import lostworlds.library.entity.navigators.SemiAquaticPathNavigator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class BreedingSemiAquaticEntity extends AnimalEntity implements ISemiAquatic
{
    public static final DataParameter<Byte> CLIMBING = EntityDataManager.defineId(BreedingSemiAquaticEntity.class, DataSerializers.BYTE);
    
    public BreedingSemiAquaticEntity(EntityType<? extends BreedingSemiAquaticEntity> entity, World world) 
	{
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.setPathfindingMalus(PathNodeType.WATER_BORDER, 0.0F);
	}
	
	@Override
	protected PathNavigator createNavigation(World world) 
	{
		SemiAquaticPathNavigator flyingpathnavigator = new SemiAquaticPathNavigator(this, world) 
		{
			@Override
			public boolean isStableDestination(BlockPos pos) 
			{
				return this.level.getBlockState(pos).getFluidState().isEmpty();
			}
		};
		return flyingpathnavigator;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if (!this.level.isClientSide) 
		{
			this.setBesideClimbableBlock(this.horizontalCollision && this.isInWater());
		}
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.set(CLIMBING, (byte) 0);
	}
	
	public boolean isBesideClimbableBlock() 
	{
		return (this.entityData.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) 
	{
		byte b0 = this.entityData.get(CLIMBING);
		if(climbing) 
		{
			b0 = (byte) (b0 | 1);
		} 
		else 
		{
			b0 = (byte) (b0 & -2);
		}

		this.entityData.set(CLIMBING, b0);
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
