package lostworlds.server.entity.aquatic;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import lostworlds.server.entity.controller.DolphinLikeLookController;
import lostworlds.server.entity.controller.DolphinLikeMovementController;
import lostworlds.server.entity.goal.aquatic.AquaticTemptGoal;
import lostworlds.server.entity.goal.aquatic.dolphin.DolphinLikeJumpGoal;
import lostworlds.server.entity.goal.aquatic.dolphin.DolphinLikePlayWithItemsGoal;
import lostworlds.server.entity.goal.aquatic.dolphin.SwimWithPlayerGoal;
import lostworlds.server.entity.utils.FoodLists;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.FollowBoatGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class DolphinLikeEntity extends BreedingWaterEntity {
	public static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(DolphinLikeEntity.class, EntityDataSerializers.INT);
	public static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = (new TargetingConditions()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
	public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itementity) -> {
		return !itementity.hasPickUpDelay() && itementity.isAlive() && itementity.isInWater();
	};
	private static final Ingredient FOOD_ITEMS = FoodLists.PISCIVORE;

	public DolphinLikeEntity(EntityType<? extends DolphinLikeEntity> entity, Level world) {
		super(entity, world);
		this.moveControl = new DolphinLikeMovementController(this);
		this.lookControl = new DolphinLikeLookController(this, 10);
		this.setCanPickUpLoot(true);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
		this.setAirSupply(this.getMaxAirSupply());
		this.xRot = 0.0F;
		return super.finalizeSpawn(world, difficulty, reason, data, nbt);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}

	protected void handleAirSupply(int air) {
	}

	public int getMoistnessLevel() {
		return this.entityData.get(MOISTNESS_LEVEL);
	}

	public void setMoisntessLevel(int moistness) {
		this.entityData.set(MOISTNESS_LEVEL, moistness);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MOISTNESS_LEVEL, 2400);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("Moistness", this.getMoistnessLevel());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.setMoisntessLevel(nbt.getInt("Moistness"));
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new BreathAirGoal(this));
		this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
		this.goalSelector.addGoal(2, new SwimWithPlayerGoal(this, 4.0D));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(5, new AquaticTemptGoal(this, 1.0F, FOOD_ITEMS));
		this.goalSelector.addGoal(5, new BreedGoal(this, 1.0F));
		this.goalSelector.addGoal(5, new DolphinLikeJumpGoal(this, 10));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
		this.goalSelector.addGoal(8, new DolphinLikePlayWithItemsGoal(this));
		this.goalSelector.addGoal(8, new FollowBoatGoal(this));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Guardian.class)).setAlertOthers());
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	public static AttributeSupplier.Builder createBasicAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double) 1.2F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = entity.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, entity);
			this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
		}

		return flag;
	}

	@Override
	public int getMaxAirSupply() {
		return 4800;
	}

	@Override
	protected int increaseAirSupply(int air) {
		return this.getMaxAirSupply();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.3F;
	}

	@Override
	public int getMaxHeadXRot() {
		return 1;
	}

	@Override
	public int getMaxHeadYRot() {
		return 1;
	}

	@Override
	protected boolean canRide(Entity entity) {
		return true;
	}

	@Override
	public boolean canTakeItem(ItemStack stack) {
		EquipmentSlot equipmentslottype = Mob.getEquipmentSlotForItem(stack);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(stack);
		}
	}

	@Override
	protected void pickUpItem(ItemEntity itementity) {
		if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
			ItemStack itemstack = itementity.getItem();
			if (this.canHoldItem(itemstack)) {
				this.onItemPickup(itementity);
				this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
				this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
				this.take(itementity, itemstack.getCount());
				itementity.remove();
			}
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isNoAi()) {
			this.setAirSupply(this.getMaxAirSupply());
		} else {
			if (this.isInWaterRainOrBubble()) {
				this.setMoisntessLevel(2400);
			} else {
				this.setMoisntessLevel(this.getMoistnessLevel() - 1);
				if (this.getMoistnessLevel() <= 0) {
					this.hurt(DamageSource.DRY_OUT, 1.0F);
				}

				if (this.onGround) {
					this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.yRot = this.random.nextFloat() * 360.0F;
					this.onGround = false;
					this.hasImpulse = true;
				}
			}

			if (this.level.isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
				Vec3 vector3d = this.getViewVector(0.0F);
				float f = Mth.cos(this.yRot * ((float) Math.PI / 180F)) * 0.3F;
				float f1 = Mth.sin(this.yRot * ((float) Math.PI / 180F)) * 0.3F;
				float f2 = 1.2F - this.random.nextFloat() * 0.7F;

				for (int i = 0; i < 2; ++i) {
					this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 + (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 + (double) f1, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vector3d.x * (double) f2 - (double) f, this.getY() - vector3d.y, this.getZ() - vector3d.z * (double) f2 - (double) f1, 0.0D, 0.0D, 0.0D);
				}
			}

		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte event) {
		if (event == 38) {
			this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
		} else {
			super.handleEntityEvent(event);
		}

	}

	@OnlyIn(Dist.CLIENT)
	private void addParticlesAroundSelf(ParticleOptions data) {
		for (int i = 0; i < 7; ++i) {
			double d0 = this.random.nextGaussian() * 0.01D;
			double d1 = this.random.nextGaussian() * 0.01D;
			double d2 = this.random.nextGaussian() * 0.01D;
			this.level.addParticle(data, this.getRandomX(1.0D), this.getRandomY() + 0.2D, this.getRandomZ(1.0D), d0, d1, d2);
		}
	}

	public static boolean canDolphinLikeSpawn(EntityType<? extends DolphinLikeEntity> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) {
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel()) {
			return world.getFluidState(pos).is(FluidTags.WATER);
		} else {
			return false;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.DOLPHIN_HURT;
	}

	@Override
	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.DOLPHIN_DEATH;
	}

	@Override
	@Nullable
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
	}

	@Override
	protected SoundEvent getSwimSplashSound() {
		return SoundEvents.DOLPHIN_SPLASH;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.DOLPHIN_SWIM;
	}

	protected boolean closeToNextPos() {
		BlockPos blockpos = this.getNavigation().getTargetPos();
		return blockpos != null ? blockpos.closerThan(this.position(), 12.0D) : false;
	}

	@Override
	public void travel(Vec3 vec3d) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(vec3d);
		}

	}

	@Override
	public boolean canBeLeashed(Player entity) {
		return true;
	}
}
