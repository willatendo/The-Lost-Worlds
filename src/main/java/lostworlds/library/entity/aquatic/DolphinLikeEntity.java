package lostworlds.library.entity.aquatic;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import lostworlds.library.entity.controller.DolphinLikeLookController;
import lostworlds.library.entity.controller.DolphinLikeMovementController;
import lostworlds.library.entity.goal.aquatic.AquaticTemptGoal;
import lostworlds.library.entity.goal.aquatic.dolphin.DolphinLikeJumpGoal;
import lostworlds.library.entity.goal.aquatic.dolphin.DolphinLikePlayWithItemsGoal;
import lostworlds.library.entity.goal.aquatic.dolphin.SwimWithPlayerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.FollowBoatGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class DolphinLikeEntity extends BreedingWaterEntity
{
	public static final DataParameter<Integer> MOISTNESS_LEVEL = EntityDataManager.defineId(DolphinLikeEntity.class, DataSerializers.INT);
	public static final EntityPredicate SWIM_WITH_PLAYER_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
	public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itementity) -> 
	{
		return !itementity.hasPickUpDelay() && itementity.isAlive() && itementity.isInWater();
	};
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

	public DolphinLikeEntity(EntityType<? extends DolphinLikeEntity> entity, World world) 
	{
		super(entity, world);
		this.moveControl = new DolphinLikeMovementController(this);
		this.lookControl = new DolphinLikeLookController(this, 10);
		this.setCanPickUpLoot(true);
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) 
	{
		this.setAirSupply(this.getMaxAirSupply());
		this.xRot = 0.0F;
		return super.finalizeSpawn(world, difficulty, reason, data, nbt);
	}

	@Override
	public boolean canBreatheUnderwater() 
	{
		return false;
	}

	protected void handleAirSupply(int air) { }
	
	public int getMoistnessLevel() 
	{
		return this.entityData.get(MOISTNESS_LEVEL);
	}

	public void setMoisntessLevel(int moistness) 
	{
		this.entityData.set(MOISTNESS_LEVEL, moistness);
	}

	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(MOISTNESS_LEVEL, 2400);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putInt("Moistness", this.getMoistnessLevel());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		this.setMoisntessLevel(nbt.getInt("Moistness"));
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(2, new SwimWithPlayerGoal(this, 4.0D));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(5, new AquaticTemptGoal(this, 1.0F, FOOD_ITEMS));
		this.goalSelector.addGoal(5, new BreedGoal(this, 1.0F));
		this.goalSelector.addGoal(5, new DolphinLikeJumpGoal(this, 10));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
		this.goalSelector.addGoal(8, new DolphinLikePlayWithItemsGoal(this));
		this.goalSelector.addGoal(8, new FollowBoatGoal(this));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, GuardianEntity.class, 8.0F, 1.0D, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, GuardianEntity.class)).setAlertOthers());
	}

	@Override
	public boolean isFood(ItemStack stack)
	{
		return FOOD_ITEMS.test(stack);
	}
	
	public static AttributeModifierMap.MutableAttribute createBasicAttributes() 
	{
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double) 1.2F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected PathNavigator createNavigation(World world) 
	{
		return new SwimmerPathNavigator(this, world);
	}

	@Override
	public boolean doHurtTarget(Entity entity) 
	{
		boolean flag = entity.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if(flag) 
		{
			this.doEnchantDamageEffects(this, entity);
			this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
		}

		return flag;
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
	protected float getStandingEyeHeight(Pose pose, EntitySize size) 
	{
		return 0.3F;
	}

	@Override
	public int getMaxHeadXRot() 
	{
		return 1;
	}

	@Override
	public int getMaxHeadYRot() 
	{
		return 1;
	}

	@Override
	protected boolean canRide(Entity entity) 
	{
		return true;
	}

	@Override
	public boolean canTakeItem(ItemStack stack) 
	{
		EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(stack);
		if(!this.getItemBySlot(equipmentslottype).isEmpty()) 
		{
			return false;
		} 
		else 
		{
			return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(stack);
		}
	}

	@Override
	protected void pickUpItem(ItemEntity itementity) 
	{
		if(this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty()) 
		{
			ItemStack itemstack = itementity.getItem();
			if(this.canHoldItem(itemstack)) 
			{
				this.onItemPickup(itementity);
				this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
				this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
				this.take(itementity, itemstack.getCount());
				itementity.remove();
			}
		}
	}

	@Override
	public void tick() 
	{
		super.tick();
		if(this.isNoAi()) 
		{
			this.setAirSupply(this.getMaxAirSupply());
		} 
		else 
		{
			if(this.isInWaterRainOrBubble()) 
			{
				this.setMoisntessLevel(2400);
			} 
			else 
			{
				this.setMoisntessLevel(this.getMoistnessLevel() - 1);
				if(this.getMoistnessLevel() <= 0) 
				{
					this.hurt(DamageSource.DRY_OUT, 1.0F);
				}

				if(this.onGround) 
				{
					this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.yRot = this.random.nextFloat() * 360.0F;
					this.onGround = false;
					this.hasImpulse = true;
				}
			}

			if(this.level.isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) 
			{
				Vector3d vector3d = this.getViewVector(0.0F);
				float f = MathHelper.cos(this.yRot * ((float) Math.PI / 180F)) * 0.3F;
				float f1 = MathHelper.sin(this.yRot * ((float) Math.PI / 180F)) * 0.3F;
				float f2 = 1.2F - this.random.nextFloat() * 0.7F;

				for(int i = 0; i < 2; ++i) 
				{
					this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 + (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 + (double) f1, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 - (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 - (double) f1, 0.0D, 0.0D, 0.0D);
				}
			}

		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte event) {
		if(event == 38) 
		{
			this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
		} 
		else 
		{
			super.handleEntityEvent(event);
		}

	}

	@OnlyIn(Dist.CLIENT)
	private void addParticlesAroundSelf(IParticleData data) 
	{
		for(int i = 0; i < 7; ++i) 
		{
			double d0 = this.random.nextGaussian() * 0.01D;
			double d1 = this.random.nextGaussian() * 0.01D;
			double d2 = this.random.nextGaussian() * 0.01D;
			this.level.addParticle(data, this.getRandomX(1.0D), this.getRandomY() + 0.2D, this.getRandomZ(1.0D), d0, d1, d2);
		}
	}
	
	public static boolean checkBasicSpawnRules(EntityType<? extends DolphinLikeEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) 
	{
		if(pos.getY() > 45 && pos.getY() < world.getSeaLevel()) 
		{
			Optional<RegistryKey<Biome>> optional = world.getBiomeName(pos);
			return (!Objects.equals(optional, Optional.of(Biomes.OCEAN)) || !Objects.equals(optional, Optional.of(Biomes.DEEP_OCEAN))) && world.getFluidState(pos).is(FluidTags.WATER);
		} 
		else 
		{
			return false;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) 
	{
		return SoundEvents.DOLPHIN_HURT;
	}

	@Override
	@Nullable
	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.DOLPHIN_DEATH;
	}

	@Override
	@Nullable
	protected SoundEvent getAmbientSound() 
	{
		return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
	}

	@Override
	protected SoundEvent getSwimSplashSound() 
	{
		return SoundEvents.DOLPHIN_SPLASH;
	}

	@Override
	protected SoundEvent getSwimSound()
	{
		return SoundEvents.DOLPHIN_SWIM;
	}

	protected boolean closeToNextPos() 
	{
		BlockPos blockpos = this.getNavigation().getTargetPos();
		return blockpos != null ? blockpos.closerThan(this.position(), 12.0D) : false;
	}

	@Override
	public void travel(Vector3d vec3d) 
	{
		if(this.isEffectiveAi() && this.isInWater()) 
		{
			this.moveRelative(this.getSpeed(), vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
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

	@Override
	public boolean canBeLeashed(PlayerEntity entity) 
	{
		return true;
	}
}
