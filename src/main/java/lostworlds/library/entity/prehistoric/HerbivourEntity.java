package lostworlds.library.entity.prehistoric;

import lostworlds.library.entity.ModDamageSources;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.entity.goal.HerbivourEatGrassGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class HerbivourEntity extends PrehistoricEntity
{
	private HerbivourEatGrassGoal eatGrassGoal;
	private int hunger;
	private int eatTimer;
	
	public HerbivourEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world, era);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.eatGrassGoal = new HerbivourEatGrassGoal(this);
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
		return this.finalizeSpawn(world, difficulty, reason, data, nbt);
	}
	
	@Override
	protected void customServerAiStep() 
	{
		this.eatTimer = this.eatGrassGoal.getEatAnimationTick();
		super.customServerAiStep();
	}
	
	@Override
	public void aiStep() 
	{
		if(this.level.isClientSide)
		{
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}
		super.aiStep();
		if(this.isAlive())
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
	
	@Override
	public void handleEntityEvent(byte id) 
	{
		if(id == 10)
		{
			this.eatTimer = 40;
		}
		else
		{
			this.handleEntityEvent(id);
		}
	}
}
