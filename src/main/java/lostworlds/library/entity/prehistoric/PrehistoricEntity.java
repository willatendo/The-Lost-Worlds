package lostworlds.library.entity.prehistoric;

import lostworlds.library.entity.TimeEras;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class PrehistoricEntity extends CreatureEntity
{
	protected static final DataParameter<Byte> SEX = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);	
	
	public static final String SEX_TAG = "Sex";
	
	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		byte sex = (byte) random.nextInt(15000);
		this.entityData.define(SEX, sex);
		this.getEntityData().define(ATTACKING, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putByte(SEX_TAG, getSex());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		setSex(nbt.getByte(SEX_TAG));
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() 
	{
		return false;
	}
	
	@Override
	public boolean removeWhenFarAway(double range) 
	{
		return false;
	}
	
	@Override
	public boolean canChangeDimensions() 
	{
		return true;
	}
	
	@Override
	public boolean canBeLeashed(PlayerEntity player) 
	{
		return true;
	}
	
	public byte getSex() 
	{
		return entityData.get(SEX);
	}
	
	public void setSex(byte sex) 
	{
		entityData.set(SEX, sex);
	}
	
	public void setAttacking(boolean attacking) 
	{
		this.entityData.set(ATTACKING, attacking);
	}
	
	public boolean isAttacking() 
	{
		return this.entityData.get(ATTACKING);
	}
	
	
}