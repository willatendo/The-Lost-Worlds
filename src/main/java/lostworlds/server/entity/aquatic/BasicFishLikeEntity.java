package lostworlds.server.entity.aquatic;

import java.util.Random;

import lostworlds.server.entity.goal.aquatic.FishLikeSwimGoal;
import lostworlds.server.entity.helper.FishLikeMoveHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public abstract class BasicFishLikeEntity extends PathfinderMob {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(BasicFishLikeEntity.class, EntityDataSerializers.BOOLEAN);

	public BasicFishLikeEntity(EntityType<? extends PathfinderMob> entity, Level world) {
		super(entity, world);
		this.moveControl = new FishLikeMoveHelper(this);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return size.height * 0.65F;
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 3.0D);
	}

	@Override
	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	public static boolean canFishLikeSpawn(EntityType<? extends BasicFishLikeEntity> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) {
		return world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
	}

	@Override
	public boolean removeWhenFarAway(double distance) {
		return !this.fromBucket() && !this.hasCustomName();
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 8;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FROM_BUCKET, false);
	}

	private boolean fromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean fromBucket) {
		this.entityData.set(FROM_BUCKET, fromBucket);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("FromBucket", this.fromBucket());
	}

	public void readAdditionalSaveData(CompoundTag p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		this.setFromBucket(p_70037_1_.getBoolean("FromBucket"));
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new FishLikeSwimGoal(this));
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	public void travel(Vec3 vec3d) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, vec3d);
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
	public void aiStep() {
		if (!this.isInWater() && this.onGround && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double) 0.4F, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.onGround = false;
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}

		super.aiStep();
	}

	@Override
	protected InteractionResult mobInteract(Player entity, InteractionHand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
			this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
			itemstack.shrink(1);
			ItemStack itemstack1 = this.getBucketItemStack();
			this.saveToBucketTag(itemstack1);
			if (!this.level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) entity, itemstack1);
			}

			if (itemstack.isEmpty()) {
				entity.setItemInHand(hand, itemstack1);
			} else if (!entity.inventory.add(itemstack1)) {
				entity.drop(itemstack1, false);
			}

			this.remove();
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			return super.mobInteract(entity, hand);
		}
	}

	protected void saveToBucketTag(ItemStack stack) {
		if (this.hasCustomName()) {
			stack.setHoverName(this.getCustomName());
		}

	}

	protected abstract ItemStack getBucketItemStack();

	public boolean canRandomSwim() {
		return true;
	}

	protected abstract SoundEvent getFlopSound();

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader reader) {
		return reader.isUnobstructed(this);
	}

	@Override
	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	protected int getExperienceReward(Player entity) {
		return 1 + this.level.random.nextInt(3);
	}

	protected void handleAirSupply(int air) {
		if (this.isAlive() && !this.isInWaterOrBubble()) {
			this.setAirSupply(air - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		} else {
			this.setAirSupply(300);
		}

	}

	@Override
	public void baseTick() {
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(Player entity) {
		return false;
	}
}
