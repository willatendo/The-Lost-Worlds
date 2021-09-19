package lostworlds.library.entity.prehistoric;

import java.util.Random;

import lostworlds.content.server.ModTags;
import lostworlds.library.entity.TimeEras;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;

public abstract class PrehistoricEntity extends AnimalEntity implements IAnimatable
{
	protected static final DataParameter<Byte> SEX = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);	
	protected static final DataParameter<Byte> PATTERN = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	
	public static final String SEX_TAG = "Sex";
	public static final String PATTERN_TAG = "Pattern";
	
	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world);
	}
	
	public static boolean canPrehistoricSpawn(EntityType type, IWorld world, SpawnReason reason, BlockPos pos, Random rand)
	{
		boolean spawnBlock = BlockTags.getAllTags().getTag(ModTags.ModBlockTags.DINO_SPAWNABLES.getName()).contains(world.getBlockState(pos.below()).getBlock());
		return spawnBlock;
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		byte sex = (byte) random.nextInt(2);
		this.entityData.define(SEX, sex);
		this.getEntityData().define(ATTACKING, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putByte(SEX_TAG, getSex());
		nbt.putByte(PATTERN_TAG, getPattern());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		setSex(nbt.getByte(SEX_TAG));
		setPattern(nbt.getByte(PATTERN_TAG));
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
	
	public byte getPattern() 
	{
		return entityData.get(PATTERN);
	}
	
	public void setPattern(byte pattern) 
	{
		entityData.set(PATTERN, pattern);
	}
	
	@Override
	public boolean canMate(AnimalEntity entity) 
	{
		PrehistoricEntity prehistoric = (PrehistoricEntity) entity;
		if(prehistoric == this)
		{
			return false;
		}
		else if(this.getSex() == prehistoric.getSex())
		{
			return false;
		}
		else if(this.getClass() != prehistoric.getClass())
		{
			return false;
		}
		else
		{
			return this.isInLove() && prehistoric.isInLove();
		}
	}
}
