package lostworlds.library.entity.terrestrial;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class TameableHerbivoreEntity extends TaggedEntity 
{
	protected static final DataParameter<Boolean> FOLLOWING = EntityDataManager.defineId(TameableHerbivoreEntity.class, DataSerializers.BOOLEAN);		
	protected static final DataParameter<Boolean> WANDERING = EntityDataManager.defineId(TameableHerbivoreEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STAYING = EntityDataManager.defineId(TameableHerbivoreEntity.class, DataSerializers.BOOLEAN);
	
	public TameableHerbivoreEntity(EntityType<? extends TaggedEntity> entity, World world) 
	{
		super(entity, world);
	}

	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Following", isFollowing());
		nbt.putBoolean("Wandering", isWandering());
		nbt.putBoolean("Staying", isStaying());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		setFollowing(nbt.getBoolean("Following"));
		setWandering(nbt.getBoolean("Wandering"));
		setStaying(nbt.getBoolean("Staying"));
	}
	
	public boolean isFollowing()
	{
		return entityData.get(FOLLOWING);
	}
	
	public boolean isWandering()
	{
		return entityData.get(WANDERING);
	}
	
	public boolean isStaying()
	{
		return entityData.get(STAYING);
	}
	
	public void setFollowing(boolean following)
	{
		entityData.set(FOLLOWING, following);;
	}
	
	public void setWandering(boolean wandering)
	{
		entityData.set(WANDERING, wandering);;
	}
	
	public void setStaying(boolean staying)
	{
		entityData.set(STAYING, staying);;
	}
	
	@SuppressWarnings("unused")
	private boolean isLookingAtMe(PlayerEntity entity) 
	{
		if(entity.getUUID() == this.getTaggedToUUID())
		{

			Vector3d vector3d = entity.getViewVector(1.0F).normalize();
			Vector3d vector3d1 = new Vector3d(this.getX() - entity.getX(), this.getEyeY() - entity.getEyeY(), this.getZ() - entity.getZ());
			double d0 = vector3d1.length();
			vector3d1 = vector3d1.normalize();
			double d1 = vector3d.dot(vector3d1);
			return d1 > 1.0D - 0.025D / d0 ? entity.canSee(this) : false;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	protected void customServerAiStep() 
	{
		
	}
}
