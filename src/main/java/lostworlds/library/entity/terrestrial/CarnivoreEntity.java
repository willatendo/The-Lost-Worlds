package lostworlds.library.entity.terrestrial;

import lostworlds.library.entity.ModDamageSources;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class CarnivoreEntity extends TaggedEntity
{
	protected static final DataParameter<Boolean> EATING = EntityDataManager.defineId(HerbivoreEntity.class, DataSerializers.BOOLEAN);
	private int hunger;
	
	public CarnivoreEntity(EntityType<? extends CarnivoreEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.getEntityData().define(EATING, false);	
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putInt("Hunger", this.getHunger());
		nbt.putBoolean("Eating", this.isEating());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		this.setHunger(nbt.getInt("Hunger"));
		this.setEating(nbt.getBoolean("Eating"));
	}
	
	public boolean isEating()
	{
		return entityData.get(EATING);
	}
	
	public void setEating(boolean eating)
	{
		entityData.set(EATING, eating);
	}
	
	public int getHunger()
	{
		return this.hunger;
	}
	
	public void setHunger(int hunger) 
	{
		this.hunger = hunger;
	}
	
	public boolean isHungry()
	{
		return this.hunger < 0 ? true : false;
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData data, CompoundNBT nbt) 
	{
		this.hunger = 21000;
		return super.finalizeSpawn(world, difficulty, reason, data, nbt);
	}
	
	@Override
	public void aiStep() 
	{
		super.aiStep();
		if(this.isAlive() && !this.isSleeping()) 
		{
			int i = this.getHunger();
			--i;
			this.setHunger(i);
		}
		if(this.getHunger() < -5000) 
		{
			this.hurt(ModDamageSources.HUNGER, 3.0F);
		}
	}
}
