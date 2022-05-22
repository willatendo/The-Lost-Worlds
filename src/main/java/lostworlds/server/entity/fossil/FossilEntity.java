package lostworlds.server.entity.fossil;

import java.util.EnumSet;

import lostworlds.server.item.ChiselItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FossilEntity extends Animal implements IAnimatable {
	private static final EntityDataAccessor<Boolean> PUSHING = SynchedEntityData.defineId(FossilEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> LOOKING = SynchedEntityData.defineId(FossilEntity.class, EntityDataSerializers.BOOLEAN);

	public static final String animation = "animation.skeleton.living";

	private AnimationFactory factory = new AnimationFactory(this);

	private ItemStack stack;

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animation, true));
		return PlayState.CONTINUE;
	}

	public FossilEntity(EntityType<? extends FossilEntity> entity, Level world) {
		super(entity, world);
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100);
	}

	public FossilEntity setPick(ItemStack stack) {
		this.stack = stack;
		return this;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return this.stack;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(PUSHING, false);
		this.entityData.define(LOOKING, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("IsPushable", this.isPushable());
		nbt.putBoolean("IsLooking", this.isLooking());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.setPushable(nbt.getBoolean("IsPushable"));
		this.setLooking(nbt.getBoolean("IsLooking"));
	}

	@Override
	public boolean isPushable() {
		return this.canBePushed();
	}

	@Override
	protected void doPush(Entity entity) {
	}

	public boolean canBePushed() {
		return this.entityData.get(PUSHING);
	}

	public void setPushable(boolean isPushable) {
		this.entityData.set(PUSHING, isPushable);
	}

	public boolean isLooking() {
		return this.entityData.get(LOOKING);
	}

	public void setLooking(boolean isLooking) {
		this.entityData.set(LOOKING, isLooking);
	}

	@Override
	protected int getExperienceReward(Player player) {
		return 0;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public void playBrokenSound() {
		this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.SKELETON_DEATH, SoundSource.BLOCKS, 1.0F, 1.0F);
	}

	public void playParticles() {
		if (this.level instanceof ServerLevel) {
			((ServerLevel) this.level).addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BONE_BLOCK.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F));
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD) {
			return super.hurt(source, amount);
		} else if (!(this instanceof DirtyFossilEntity)) {
			if (source.getDirectEntity() instanceof Player) {
				Player player = (Player) source.getDirectEntity();
				if (player.getMainHandItem().getItem() instanceof ChiselItem) {
					ItemStack stack = player.getMainHandItem();

					stack.hurtAndBreak(1, player, (playerentity) -> {
						playerentity.broadcastBreakEvent(player.getUsedItemHand());
					});
					this.remove();
					if (!this.level.isClientSide)
						this.dropAllDeathLoot(source);
					this.playBrokenSound();
					this.playParticles();
				}
			}
		}
		return false;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	protected boolean shouldDropLoot() {
		return true;
	}

	@Override
	public AgableMob getBreedOffspring(ServerLevel world, AgableMob entity) {
		return null;
	}

	static class LookAtPlayerGoal extends Goal {
		protected final FossilEntity entity;
		protected Entity lookAt;
		protected final float range;
		private int lookTime;
		protected final float probability;
		protected final Class<? extends LivingEntity> lookAtType;
		protected final TargetingConditions lookAtContext;

		public LookAtPlayerGoal(FossilEntity entity) {
			this(entity, Player.class, 32.0F, 0.02F);
		}

		public LookAtPlayerGoal(FossilEntity entity, Class<? extends LivingEntity> lookAtEntity, float range, float probability) {
			this.entity = entity;
			this.lookAtType = lookAtEntity;
			this.range = range;
			this.probability = probability;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
			if (lookAtEntity == Player.class) {
				this.lookAtContext = (new TargetingConditions()).range((double) range).allowSameTeam().allowInvulnerable().allowNonAttackable().selector((livingentity) -> {
					return EntitySelector.notRiding(entity).test(livingentity);
				});
			} else {
				this.lookAtContext = (new TargetingConditions()).range((double) range).allowSameTeam().allowInvulnerable().allowNonAttackable();
			}

		}

		@Override
		public boolean canUse() {
			if (this.entity.isLooking()) {
				if (this.entity.getTarget() != null) {
					this.lookAt = this.entity.getTarget();
				}

				if (this.lookAtType == Player.class) {
					this.lookAt = this.entity.level.getNearestPlayer(this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
				} else {
					this.lookAt = this.entity.level.getNearestLoadedEntity(this.lookAtType, this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ(), this.entity.getBoundingBox().inflate((double) this.range, 3.0D, (double) this.range));
				}

				return this.lookAt != null;
			} else {
				return false;
			}
		}

		@Override
		public boolean canContinueToUse() {
			if (!this.lookAt.isAlive()) {
				return false;
			} else if (this.entity.distanceToSqr(this.lookAt) > (double) (this.range * this.range)) {
				return false;
			} else {
				return this.lookTime > 0 && entity.isLooking();
			}
		}

		@Override
		public void start() {
			this.lookTime = 40 + this.entity.getRandom().nextInt(40);
		}

		@Override
		public void stop() {
			this.lookAt = null;
		}

		@Override
		public void tick() {
			this.entity.getLookControl().setLookAt(this.lookAt.getX(), this.lookAt.getEyeY(), this.lookAt.getZ());
			--this.lookTime;
		}
	}
}
