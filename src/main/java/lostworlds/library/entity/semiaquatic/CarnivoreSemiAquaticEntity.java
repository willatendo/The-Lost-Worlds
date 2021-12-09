package lostworlds.library.entity.semiaquatic;

import java.util.Random;

import lostworlds.library.entity.controller.SemiAquaticMoveController;
import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import lostworlds.library.entity.utils.ISemiAquatic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class CarnivoreSemiAquaticEntity extends CarnivoreEntity implements ISemiAquatic
{    
	public static final DataParameter<BlockPos> TRAVEL_POS = EntityDataManager.defineId(CarnivoreSemiAquaticEntity.class, DataSerializers.BLOCK_POS);
	public final Random random = new Random();
	private boolean isLandNavigator;
	
    public CarnivoreSemiAquaticEntity(EntityType<? extends CarnivoreSemiAquaticEntity> entity, World world) 
	{
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.setPathfindingMalus(PathNodeType.WATER_BORDER, 0.0F);
		switchNavigator(false);
	}

    public void setTravelPos(BlockPos pos) 
	{
		this.entityData.set(TRAVEL_POS, pos);
	}

    public BlockPos getTravelPos() 
	{
		return this.entityData.get(TRAVEL_POS);
	}
	
	private void switchNavigator(boolean onLand) 	
	{
		if(onLand) 
		{
			this.moveControl = new MovementController(this);
			this.navigation = new GroundPathNavigator(this, this.level);
			this.isLandNavigator = true;
		} 
		else 
		{
			this.moveControl = new SemiAquaticMoveController(this);
			this.navigation = new SwimmerPathNavigator(this, this.level);
			this.isLandNavigator = false;
		}
	}
	
	public abstract double getInWaterSpeed();

	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putInt("TravelPosX", this.getTravelPos().getX());
		nbt.putInt("TravelPosY", this.getTravelPos().getY());
		nbt.putInt("TravelPosZ", this.getTravelPos().getZ());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		int l = nbt.getInt("TravelPosX");
		int i1 = nbt.getInt("TravelPosY");
		int j1 = nbt.getInt("TravelPosZ");
		this.setTravelPos(new BlockPos(l, i1, j1));
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
		return false;
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
		this.setTravelPos(BlockPos.ZERO);
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
		
		boolean ground = !this.isInWaterOrBubble();
		
		if(!ground && this.isLandNavigator) 
		{
			switchNavigator(false);
		}
		if(ground && !this.isLandNavigator) 
		{
			switchNavigator(true);
		}
		
		super.tick();
	}
	
	@Override
	public void travel(Vector3d vec3d) 
	{
		if(!this.level.isClientSide() && this.isInWater()) 
		{
			this.moveRelative(this.getSpeed(), vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(getInWaterSpeed()));
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
}
