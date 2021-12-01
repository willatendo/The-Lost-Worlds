package lostworlds.library.entity.terrestrial;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.ModTags;
import lostworlds.content.server.init.ItemInit;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;

public abstract class PrehistoricEntity extends AgeableEntity implements ITyrannomatable
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	
	protected static final DataParameter<Byte> SEX = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);		
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> CONTRACEPTIVES = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BOOLEAN);
	
	public static final String SEX_TAG = "Sex";
	public static final String PATTERN_TAG = "Pattern";
	
	public int inNaturalLove;
	public UUID cause;
	
	public int inLove;
	public UUID loveCause;
				
	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world) 
	{
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 16.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
	}
	
	@Override
	protected void customServerAiStep() 
	{
		if(this.getAge() != 0) 
		{
			this.inNaturalLove = 0;
		}

		if(this.getAge() != 0) 
		{
			this.inLove = 0;
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
			if(this.level.getNearbyEntities(this.getClass(), PARTNER_TARGETING, this, this.getBoundingBox().inflate(LostWorldsConfig.COMMON_CONFIG.maxSearchRange.get())).size() < LostWorldsConfig.COMMON_CONFIG.maxDinoGroup.get() && this.level.getNearbyEntities(this.getClass(), PARTNER_TARGETING, this, this.getBoundingBox().inflate(LostWorldsConfig.COMMON_CONFIG.maxSearchRange.get())).size() > 1) 
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
			this.inLove = 0;
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
		this.getEntityData().define(ATTACKING, false);
		this.entityData.define(SLEEPING, false);
		this.entityData.define(CONTRACEPTIVES, false);
		byte sex = (byte) random.nextInt(2);
		this.entityData.define(SEX, sex);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Sleeping", isSleeping());
		nbt.putBoolean("Contraceptives", isOnContraceptives());
		nbt.putInt("InNaturalLove", this.inNaturalLove);

		nbt.putByte(SEX_TAG, getSex());
		if(this.cause != null) 
		{
			nbt.putUUID("Cause", this.cause);
		}
		nbt.putInt("InLove", this.inLove);
		if(this.loveCause != null) 
		{
			nbt.putUUID("LoveCause", this.loveCause);
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		setSleeping(nbt.getBoolean("Sleeping"));
		setOnContraceptives(nbt.getBoolean("Contraceptives"));
		this.inNaturalLove = nbt.getInt("InNaturalLove");
		setSex(nbt.getByte(SEX_TAG));
		this.cause = nbt.hasUUID("Cause") ? nbt.getUUID("Cause") : null;
		this.inLove = nbt.getInt("InLove");
		this.loveCause = nbt.hasUUID("LoveCause") ? nbt.getUUID("LoveCause") : null;
	}
	
	@Override
	public int getAmbientSoundInterval() 
	{
		return 120;
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

	@Override
	protected int getExperienceReward(PlayerEntity entity) 
	{
		return 1 + this.level.random.nextInt(3);
	}
	
	public boolean isFood(ItemStack stack) 
	{
		return stack.getItem() == Items.WHEAT;
	}
	
	public boolean isContraceptives(ItemStack stack) 
	{
		return stack.getItem() == ItemInit.CONTRACEPTIVES;
	}
	
	public byte getSex() 
	{
		return entityData.get(SEX);
	}
	
	public void setSex(byte sex) 
	{
		entityData.set(SEX, sex);
	}
	
	public boolean isOnContraceptives() 
	{
		return entityData.get(CONTRACEPTIVES);
	}
	
	public void setOnContraceptives(boolean contraceptives) 
	{
		entityData.set(CONTRACEPTIVES, contraceptives);
	}
	
	public void setAttacking(boolean attacking) 
	{
		this.entityData.set(ATTACKING, attacking);
	}
	
	public boolean isAttacking() 
	{
		return this.entityData.get(ATTACKING);
	}
	
	public boolean isSleeping()
	{
		return entityData.get(SLEEPING);
	}
	
	public void setSleeping(boolean sleeping)
	{
		entityData.set(SLEEPING, sleeping);
	}
	
	public boolean canFallInLove() 
	{
		return this.inLove <= 0;
	}
	
	public void setInLove(@Nullable PlayerEntity entity) 
	{
		this.inLove = 600;
		if(entity != null) 
		{
			this.loveCause = entity.getUUID();
		}
		
		this.level.broadcastEntityEvent(this, (byte)18);
	}
	
	public void setInLoveTime(int love) 
	{
		this.inLove = love;
	}
	
	public int getInLoveTime() 
	{
		return this.inLove;
	}
	
	@Nullable
	public ServerPlayerEntity getLoveCause() 
	{
		if(this.loveCause == null) 
		{
			return null;
		} 
		else 
		{
			PlayerEntity playerentity = this.level.getPlayerByUUID(this.loveCause);
			return playerentity instanceof ServerPlayerEntity ? (ServerPlayerEntity)playerentity : null;
		}
	}
	
	public boolean isInLove() 
	{
		return this.inLove > 0;
	}
	
	public void resetLove() 
	{
		this.inLove = 0;
	}
	
	@Override
	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) 
	{
		ItemStack itemstack = entity.getItemInHand(hand);
		if(this.isFood(itemstack)) 
		{
			int i = this.getAge();
			if(!this.level.isClientSide && i == 0 && this.canFallInLove()) 
			{
				this.usePlayerItem(entity, itemstack);
				this.setInLove(entity);
				return ActionResultType.SUCCESS;
			}
			
			if(this.isBaby()) 
			{
				this.usePlayerItem(entity, itemstack);
				this.ageUp((int)((float)(-i / 20) * 0.1F), true);
				return ActionResultType.sidedSuccess(this.level.isClientSide);
			}
			
			if(this.level.isClientSide) 
			{
				return ActionResultType.CONSUME;
			}
		}
		if(this.isContraceptives(itemstack) && !this.isOnContraceptives()) 
		{
			if(!this.level.isClientSide && !this.isBaby()) 
			{
				this.usePlayerItem(entity, itemstack);
				this.setOnContraceptives(true);
				return ActionResultType.SUCCESS;
			}
		}
			
		return super.mobInteract(entity, hand);
	}
	
	protected void usePlayerItem(PlayerEntity entity, ItemStack stack) 
	{
		if(!entity.abilities.instabuild) 
		{
			stack.shrink(1);
		}
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
	
	public boolean canMate(PrehistoricEntity entity) 
	{
		PrehistoricEntity prehistoric = entity;
		if(prehistoric == this)
		{
			return false;
		}
		else if(this.isOnContraceptives())
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
	
	public void spawnChildFromBreeding(ServerWorld world, PrehistoricEntity partner) 
	{
		AgeableEntity ageableentity = this.getBreedOffspring(world, partner);
		final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, partner, ageableentity);
		final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
		ageableentity = event.getChild();
		if(cancelled) 
		{
			this.setAge(6000);
			partner.setAge(6000);
			this.resetLove();
			partner.resetLove();
			return;
		}
		if(ageableentity != null) 
		{
			ServerPlayerEntity serverplayerentity = this.getLoveCause();
			if(serverplayerentity == null && partner.getLoveCause() != null) 
			{
				serverplayerentity = partner.getLoveCause();
			}
			
			if(serverplayerentity != null) 
			{
				serverplayerentity.awardStat(Stats.ANIMALS_BRED);
			}
			
			this.setAge(6000);
			partner.setAge(6000);
			this.resetLove();
			partner.resetLove();
			ageableentity.setBaby(true);
			ageableentity.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			world.addFreshEntityWithPassengers(ageableentity);
			world.broadcastEntityEvent(this, (byte)18);
			if(world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) 
			{
				world.addFreshEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
			}
		}
	}
}
