package lostworlds.server.entity.terrestrial;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticMob;
import lostworlds.server.entity.utils.ModDamageSources;
import lostworlds.server.entity.utils.TabletInfo;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.species.SpeciesType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public abstract class PrehistoricMob extends Animal implements IAnimatable, TabletInfo {
	protected static final EntityDataAccessor<Byte> VARIENT = SynchedEntityData.defineId(PrehistoricMob.class, EntityDataSerializers.BYTE);
	protected static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(PrehistoricMob.class, EntityDataSerializers.BYTE);

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
			if (this.isInWaterOrBubble() && this instanceof CarnivoreSemiAquaticMob) {
				controller.setAnimation(SWIM_ANIMATION);
			} else {
				controller.setAnimation(isMoving ? WALK_ANIMATION : IDLE_ANIMATION);
			}
			break;
		}

		return PlayState.CONTINUE;
	}

	public PrehistoricMob(EntityType<? extends PrehistoricMob> entity, Level world) {
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

		this.level.playSound(null, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, this.getSoundVolume(), this.getVoicePitch());
		return true;
	}

	public boolean isHungry() {
		return this.hunger < 0 ? true : false;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData data, CompoundTag nbt) {
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

	public static boolean canPrehistoricSpawn(EntityType type, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) {
		boolean spawnBlock = world.getBlockState(pos.below()).is(LostWorldsTags.ModBlockTags.DINO_SPAWNABLES.tag);
		return spawnBlock;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ANIMATION, ANIMATION_IDLE);
		byte varient;
		if (this instanceof SpeciesTagModelAndTextureable species) {
			List<SpeciesType> types = LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get().tags().getTag(species.getTagToUse()).stream().toList();
			varient = (byte) this.random.nextInt(types.size());
		} else {
			varient = (byte) random.nextInt(2);
		}
		this.entityData.define(VARIENT, varient);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Contraceptives", isOnContraceptives());
		nbt.putInt("Hunger", this.getHunger());
		nbt.putByte("Varient", getVarient());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
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
	public boolean canBeLeashed(Player player) {
		return true;
	}

	@Override
	protected int getExperienceReward(Player entity) {
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

	public void setInLove(@Nullable Player entity) {
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
	public ServerPlayer getLoveCause() {
		if (this.loveCause == null) {
			return null;
		} else {
			Player playerentity = this.level.getPlayerByUUID(this.loveCause);
			return playerentity instanceof ServerPlayer ? (ServerPlayer) playerentity : null;
		}
	}

	public boolean isInLove() {
		return this.inLove > 0;
	}

	public void resetLove() {
		this.inLove = 0;
	}

	@Override
	public InteractionResult mobInteract(Player entity, InteractionHand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		if (this.isFood(itemstack)) {
			int i = this.getAge();
			if (!this.level.isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(entity, itemstack);
				this.setInLove(entity);
				return InteractionResult.SUCCESS;
			}

			if (this.isBaby()) {
				this.usePlayerItem(entity, itemstack);
				this.ageUp((int) ((float) (-i / 20) * 0.1F), true);
				return InteractionResult.sidedSuccess(this.level.isClientSide);
			}

			if (this.level.isClientSide) {
				return InteractionResult.CONSUME;
			}

			this.increaseHunger(this.maxHunger());
		}
		if (this.isContraceptives(itemstack) && !this.isOnContraceptives()) {
			if (!this.level.isClientSide && !this.isBaby()) {
				this.usePlayerItem(entity, itemstack);
				this.setOnContraceptives(true);
				return InteractionResult.SUCCESS;
			}
		}

		return super.mobInteract(entity, hand);
	}

	protected void usePlayerItem(Player entity, ItemStack stack) {
		if (!entity.isCreative()) {
			stack.shrink(1);
		}
	}
}
