package lostworlds.server.entity.terrestrial;

import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticEntity;
import lostworlds.server.entity.utils.IForTabletThings;
import lostworlds.server.entity.utils.ModDamageSources;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public abstract class PrehistoricEntity extends AnimalEntity implements IAnimatable, IForTabletThings {
	protected static final DataParameter<Byte> VARIENT = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> ANIMATION = EntityDataManager.defineId(PrehistoricEntity.class, DataSerializers.BYTE);

	public static final byte ANIMATION_IDLE = 0;
	public static final byte ANIMATION_SLEEP = 1;
	public static final byte ANIMATION_EAT = 2;
	public static final byte ANIMATION_GLIDE = 3;
	public static final byte ANIMATION_WALL_WALK = 4;
	public static final byte ANIMATION_FLY = 5;

	public static final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
	public static final AnimationBuilder SWIM_ANIMATION = new AnimationBuilder().addAnimation("into_swim").addAnimation("swim", true).addAnimation("out_of_swim");
	public static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
	public static final AnimationBuilder GLIDE_ANIMATION = new AnimationBuilder().addAnimation("into_glide").addAnimation("glide", true).addAnimation("out_of_glide");
	public static final AnimationBuilder SLEEP_ANIMATION = new AnimationBuilder().addAnimation("into_sleep").addAnimation("sleep", true).addAnimation("out_of_sleep");
	public static final AnimationBuilder EAT_ANIMATION = new AnimationBuilder().addAnimation("eat", false);
	public static final AnimationBuilder WALL_WALK_ANIMATION = new AnimationBuilder().addAnimation("into_wall_walk").addAnimation("wall_walk", true).addAnimation("out_of_wall_walk");
	public static final AnimationBuilder FLY_ANIMATION = new AnimationBuilder().addAnimation("fly_animation", true);

	private int hunger;
	private boolean contraceptives;

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		float limbSwingAmount = event.getLimbSwingAmount();
		boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);
		AnimationController controller = event.getController();

		byte currentAnimation = this.getAnimation();
		switch (currentAnimation) {
		case ANIMATION_SLEEP:
			controller.setAnimation(SLEEP_ANIMATION);
			break;
		case ANIMATION_EAT:
			controller.setAnimation(EAT_ANIMATION);
			break;
		case ANIMATION_GLIDE:
			controller.setAnimation(GLIDE_ANIMATION);
			break;
		case ANIMATION_WALL_WALK:
			controller.setAnimation(WALL_WALK_ANIMATION);
			break;
		case ANIMATION_FLY:
			controller.setAnimation(FLY_ANIMATION);
			break;
		default:
			if (this.isInWaterOrBubble() && this instanceof CarnivoreSemiAquaticEntity) {
				controller.setAnimation(SWIM_ANIMATION);
			} else {
				controller.setAnimation(isMoving ? WALK_ANIMATION : IDLE_ANIMATION);
			}
			break;
		}

		return PlayState.CONTINUE;
	}

	public PrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world) {
		super(entity, world);
	}

	public boolean isHungry(LivingEntity entity) {
		return this.isHungry();
	}

	public abstract int maxHunger();

	public boolean isSleeping() {
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_SLEEP;
	}

	public boolean isEating() {
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_EAT;
	}

	public boolean isGliding() {
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_GLIDE;
	}

	public boolean isFlying() {
		byte currentAnimation = this.getAnimation();
		return currentAnimation == ANIMATION_FLY;
	}

	public int getHunger() {
		return this.hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public void addHunger(int hunger) {
		this.hunger = this.getHunger() + hunger;
	}

	public boolean increaseHunger(int hunger) {
		if (this.getHunger() >= this.maxHunger()) {
			return false;
		}

		this.setHunger(this.getHunger() + hunger);

		if (this.getHunger() > this.maxHunger()) {
			this.setHunger(this.maxHunger());
		}

		this.level.playSound(null, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getVoicePitch());
		return true;
	}

	public boolean isHungry() {
		return this.hunger < 0 ? true : false;
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData data, CompoundNBT nbt) {
		this.hunger = this.maxHunger();
		return super.finalizeSpawn(world, difficulty, reason, data, nbt);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.isAlive() && !this.isSleeping()) {
			int hunger = this.getHunger();
			--hunger;
			this.setHunger(hunger);
		}

		if (this.getHunger() < -5000) {
			this.hurt(ModDamageSources.HUNGER, 3.0F);
		}
	}

	public static boolean canPrehistoricSpawn(EntityType type, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
		boolean spawnBlock = BlockTags.getAllTags().getTag(LostWorldsTags.ModBlockTags.DINO_SPAWNABLES.tag.getName()).contains(world.getBlockState(pos.below()).getBlock());
		return spawnBlock;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ANIMATION, ANIMATION_IDLE);
		byte varient = (byte) random.nextInt(2);
		this.entityData.define(VARIENT, varient);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Contraceptives", isOnContraceptives());
		nbt.putInt("Hunger", this.getHunger());
		nbt.putByte("Varient", getVarient());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		this.setOnContraceptives(nbt.getBoolean("Contraceptives"));
		this.setVarient(nbt.getByte("Varient"));
		this.setHunger(nbt.getInt("Hunger"));
	}

	@Override
	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double range) {
		return false;
	}

	@Override
	public boolean canChangeDimensions() {
		return true;
	}

	@Override
	public boolean canBeLeashed(PlayerEntity player) {
		return true;
	}

	@Override
	protected int getExperienceReward(PlayerEntity entity) {
		return 1 + this.level.random.nextInt(3);
	}

	public boolean isFood(ItemStack stack) {
		return stack.getItem() == Items.WHEAT;
	}

	public boolean isContraceptives(ItemStack stack) {
		return stack.getItem() == LostWorldsItems.CONTRACEPTIVES.get();
	}

	public byte getVarient() {
		return entityData.get(VARIENT);
	}

	public void setVarient(byte varient) {
		entityData.set(VARIENT, varient);
	}

	public boolean isOnContraceptives() {
		return contraceptives;
	}

	public void setOnContraceptives(boolean contraceptives) {
		this.contraceptives = contraceptives;
	}

	public void setAnimation(byte animation) {
		this.entityData.set(ANIMATION, animation);
	}

	public byte getAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public boolean canFallInLove() {
		return this.inLove <= 0;
	}

	public void setInLove(@Nullable PlayerEntity entity) {
		this.inLove = 600;
		if (entity != null) {
			this.loveCause = entity.getUUID();
		}

		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	public void setInLoveTime(int love) {
		this.inLove = love;
	}

	public int getInLoveTime() {
		return this.inLove;
	}

	@Nullable
	public ServerPlayerEntity getLoveCause() {
		if (this.loveCause == null) {
			return null;
		} else {
			PlayerEntity playerentity = this.level.getPlayerByUUID(this.loveCause);
			return playerentity instanceof ServerPlayerEntity ? (ServerPlayerEntity) playerentity : null;
		}
	}

	public boolean isInLove() {
		return this.inLove > 0;
	}

	public void resetLove() {
		this.inLove = 0;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		if (this.isFood(itemstack)) {
			int i = this.getAge();
			if (!this.level.isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(entity, itemstack);
				this.setInLove(entity);
				return ActionResultType.SUCCESS;
			}

			if (this.isBaby()) {
				this.usePlayerItem(entity, itemstack);
				this.ageUp((int) ((float) (-i / 20) * 0.1F), true);
				return ActionResultType.sidedSuccess(this.level.isClientSide);
			}

			if (this.level.isClientSide) {
				return ActionResultType.CONSUME;
			}

			this.increaseHunger(this.maxHunger());
		}
		if (this.isContraceptives(itemstack) && !this.isOnContraceptives()) {
			if (!this.level.isClientSide && !this.isBaby()) {
				this.usePlayerItem(entity, itemstack);
				this.setOnContraceptives(true);
				return ActionResultType.SUCCESS;
			}
		}

		return super.mobInteract(entity, hand);
	}

	protected void usePlayerItem(PlayerEntity entity, ItemStack stack) {
		if (!entity.abilities.instabuild) {
			stack.shrink(1);
		}
	}
}
