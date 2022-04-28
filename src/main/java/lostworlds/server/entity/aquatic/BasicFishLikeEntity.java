package lostworlds.server.entity.aquatic;

import java.util.Random;

import lostworlds.server.entity.goal.aquatic.FishLikeSwimGoal;
import lostworlds.server.entity.helper.FishLikeMoveHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public abstract class BasicFishLikeEntity extends CreatureEntity {
	private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.defineId(BasicFishLikeEntity.class, DataSerializers.BOOLEAN);

	public BasicFishLikeEntity(EntityType<? extends CreatureEntity> entity, World world) {
		super(entity, world);
		this.moveControl = new FishLikeMoveHelper(this);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.65F;
	}

	public static MutableAttribute createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 3.0D);
	}

	@Override
	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	public static boolean canFishLikeSpawn(EntityType<? extends BasicFishLikeEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
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
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("FromBucket", this.fromBucket());
	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		this.setFromBucket(p_70037_1_.getBoolean("FromBucket"));
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new FishLikeSwimGoal(this));
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new SwimmerPathNavigator(this, world);
	}

	@Override
	public void travel(Vector3d vec3d) {
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
	protected ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
			this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
			itemstack.shrink(1);
			ItemStack itemstack1 = this.getBucketItemStack();
			this.saveToBucketTag(itemstack1);
			if (!this.level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) entity, itemstack1);
			}

			if (itemstack.isEmpty()) {
				entity.setItemInHand(hand, itemstack1);
			} else if (!entity.inventory.add(itemstack1)) {
				entity.drop(itemstack1, false);
			}

			this.remove();
			return ActionResultType.sidedSuccess(this.level.isClientSide);
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
	public CreatureAttribute getMobType() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(IWorldReader reader) {
		return reader.isUnobstructed(this);
	}

	@Override
	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	protected int getExperienceReward(PlayerEntity entity) {
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

	public boolean canBeLeashed(PlayerEntity entity) {
		return false;
	}
}
