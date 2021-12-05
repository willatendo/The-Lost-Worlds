package lostworlds.library.entity.terrestrial;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.ModTags;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.entity.utils.IForTabletThings;
import lostworlds.library.entity.utils.ModDamageSources;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;

public abstract class PrehistoricEntity extends AgeableEntity implements ITyrannomatable, IForTabletThings
{
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
	
	protected static final DataParameter<Byte> VARIENT = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> ANIMATION = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);

	public static final byte ANIMATION_IDLE = 0;
	public static final byte ANIMATION_SLEEP = 1;
	public static final byte ANIMATION_EAT = 1;
	
	public static final TyrannomationBuilder WALK_ANIMATION = new TyrannomationBuilder().addAnimation("walk", true);
	public static final TyrannomationBuilder IDLE_ANIMATION = new TyrannomationBuilder().addAnimation("idle", true);
	public static final TyrannomationBuilder SLEEP_ANIMATION = new TyrannomationBuilder().addAnimation("into_sleep").addAnimation("sleep").addAnimation("out_of_sleep");
	public static final TyrannomationBuilder EAT_ANIMATION = new TyrannomationBuilder().addAnimation("eat", false);
		
	public int inNaturalLove;
	public UUID cause;
	
	public int inLove;
	public UUID loveCause;

	private int hunger;
	
	private boolean contraceptives;
				
	public <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);
		TyrannomationController controller = event.getController();
		
		byte currentAnimation = this.getAnimation();
		switch(currentAnimation) 
		{
			case ANIMATION_SLEEP:
				controller.setAnimation(SLEEP_ANIMATION);
				break;
			default:
				controller.setAnimation(isMoving ? WALK_ANIMATION : IDLE_ANIMATION);
				break;
		}
		
		return PlayState.CONTINUE;
	}
	
	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world) 
	{
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 16.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
	}
	
	public abstract int maxHunger();
	
	public boolean isSleeping()
	{
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_SLEEP;
	}
	
	public boolean isEating()
	{
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_EAT;
	}
	
	public int getHunger()
	{
		return this.hunger;
	}
	
	public void setHunger(int hunger) 
	{
		this.hunger = hunger;
	}
	
	public void addHunger(int hunger) 
	{
		this.hunger = this.getHunger() + hunger;
	}
	
	public boolean increaseHunger(int hunger) 
	{
		if(this.getHunger() >= this.maxHunger()) 
		{
			return false;
		}
		
		this.setHunger(this.getHunger() + hunger);
		
		if(this.getHunger() > this.maxHunger()) 
		{
			this.setHunger(this.maxHunger());
		}
		
		this.level.playSound(null, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getVoicePitch());
		return true;
	}
	
	public boolean isHungry()
	{
		return this.hunger < 0 ? true : false;
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData data, CompoundNBT nbt) 
	{
		this.hunger = this.maxHunger();
		return super.finalizeSpawn(world, difficulty, reason, data, nbt);
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
		
		if(this.isAlive() && !this.isSleeping())
		{
			int hunger = this.getHunger();
			--hunger;
			this.setHunger(hunger);
		}
		
		if(this.getHunger() < -5000)
		{
			this.hurt(ModDamageSources.HUNGER, 3.0F);
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
		this.entityData.define(ANIMATION, ANIMATION_IDLE);
		byte varient = (byte) random.nextInt(2);
		this.entityData.define(VARIENT, varient);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Contraceptives", isOnContraceptives());
		nbt.putInt("InNaturalLove", this.inNaturalLove);
		nbt.putInt("Hunger", this.getHunger());
		nbt.putByte("Varient", getVarient());
		
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
		this.setOnContraceptives(nbt.getBoolean("Contraceptives"));
		this.inNaturalLove = nbt.getInt("InNaturalLove");
		this.setVarient(nbt.getByte("Varient"));
		this.setHunger(nbt.getInt("Hunger"));
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
	
	public byte getVarient() 
	{
		return entityData.get(VARIENT);
	}
	
	public void setVarient(byte varient) 
	{
		entityData.set(VARIENT, varient);
	}
	
	public boolean isOnContraceptives() 
	{
		return contraceptives;
	}
	
	public void setOnContraceptives(boolean contraceptives) 
	{
		this.contraceptives = contraceptives;
	}
	
	public void setAnimation(byte animation) 
	{
		this.entityData.set(ANIMATION, animation);
	}
	
	public byte getAnimation() 
	{
		return this.entityData.get(ANIMATION);
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
