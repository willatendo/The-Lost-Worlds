package lostworlds.library.entity.prehistoric;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.ModTags;
import lostworlds.library.entity.TimeEras;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import software.bernie.geckolib3.core.IAnimatable;

public abstract class PrehistoricEntity extends AnimalEntity implements IAnimatable
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	
	protected static final DataParameter<Byte> SEX = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);	
	protected static final DataParameter<Byte> PATTERN = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	
	public static final String SEX_TAG = "Sex";
	public static final String PATTERN_TAG = "Pattern";
	
	public int inNaturalLove;
	public UUID cause;
	
	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world);
	}
	
	@Override
	protected void customServerAiStep() 
	{
		if(this.getAge() != 0) 
		{
			this.inNaturalLove = 0;
		}
		
		super.customServerAiStep();
	}
	
	@Override
	public void aiStep() 
	{
		super.aiStep();
		if(this.getAge() != 0) 
		{
			this.inNaturalLove = 0;
		}
		
		if(this.inNaturalLove > 0)
		{
			--this.inNaturalLove;
		}
		
		int i = this.getAge();
		if(i == 0 && this.canFallInNaturalLove())
		{			
			if(this.level.getNearbyEntities(this.getClass(), PARTNER_TARGETING, this, this.getBoundingBox().inflate(LostWorldsConfig.COMMON_CONFIG.maxSearchRange.get())).size() < LostWorldsConfig.COMMON_CONFIG.maxDinoGroup.get())
			{
				this.setInNaturalLove(this);
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float damage) 
	{
		if(this.isInvulnerableTo(source)) 
		{
			return false;
		} 
		else 
		{
			this.inNaturalLove = 0;
			return super.hurt(source, damage);
		}
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
		nbt.putInt("InNaturalLove", this.inNaturalLove);
		if(this.cause != null) 
		{
			nbt.putUUID("Cause", this.cause);
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		setSex(nbt.getByte(SEX_TAG));
		setPattern(nbt.getByte(PATTERN_TAG));
		this.inNaturalLove = nbt.getInt("InNaturalLove");
		this.cause = nbt.hasUUID("Cause") ? nbt.getUUID("Cause") : null;
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
	
	@Nullable
	protected PrehistoricEntity getUUID(UUID uuid) 
	{
		if(uuid.equals(this.getUUID()))
		{
			PrehistoricEntity prehistoric = this.getUUID(uuid);
			return prehistoric;
		}
		return null;
	}
	
	public boolean canFallInNaturalLove() 
	{
		return this.inNaturalLove <= 0 || !this.isBaby();
	}
	
	public void setInNaturalLove(@Nullable PrehistoricEntity entity) 
	{
		this.inNaturalLove = 600;
		if(entity != null) 
		{
			this.cause = entity.getUUID();
		}
		
		this.level.broadcastEntityEvent(this, (byte)18);
	}
	
	public void setInNaturalLoveTime(int love) 
	{
		this.inNaturalLove = love;
	}
	
	public int getInNaturalLoveTime() 
	{
		return this.inNaturalLove;	   
	}
	
	public boolean isInNaturalLove() 
	{
		return this.inNaturalLove > 0;
	}
	
	public void resetNaturalLove() 
	{
		this.inNaturalLove = 0;
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
			return this.isInLove() && prehistoric.isInLove() || this.isInNaturalLove() && prehistoric.isInNaturalLove();
		}
	}
	
	public void spawnChildFromNaturalBreeding(ServerWorld world, PrehistoricEntity entity) 
	{
		AgeableEntity ageableentity = this.getBreedOffspring(world, entity);
		final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, entity, ageableentity);
		final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
		ageableentity = event.getChild();
		if(cancelled) 
		{
			this.setAge(6000);
			entity.setAge(6000);
			this.resetNaturalLove();
			entity.resetNaturalLove();
			return;
		}
		if(ageableentity != null) 
		{
			this.setAge(6000);
			entity.setAge(6000);	         
			this.resetNaturalLove();
			entity.resetNaturalLove();
			ageableentity.setBaby(true);
			ageableentity.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			world.addFreshEntityWithPassengers(ageableentity);
			world.broadcastEntityEvent(this, (byte)18);
		}
	}
}
