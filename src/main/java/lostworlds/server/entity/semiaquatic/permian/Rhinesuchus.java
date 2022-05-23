package lostworlds.server.entity.semiaquatic.permian;

import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.goal.ReasonedAttackableTargetGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticFindWaterGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticLeaveWaterGoal;
import lostworlds.server.entity.goal.terrestrial.SleepGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookAtPlayerGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyWaterAvoidingRandomStrollGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialReasonableAttackGoal;
import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticMob;
import lostworlds.server.entity.semiaquatic.jurassic.Protosuchus;
import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import lostworlds.server.entity.utils.FoodLists;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.util.IngredientUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Rhinesuchus extends CarnivoreSemiAquaticMob implements NeutralMob {
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Rhinesuchus.class, EntityDataSerializers.INT);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	public static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(Rhinesuchus.class, EntityDataSerializers.INT);
	private static final Ingredient FOOD_ITEMS = IngredientUtil.combine(FoodLists.CARNIVORE, FoodLists.PISCIVORE);
	private AnimationFactory factory = new AnimationFactory(this);
	private UUID persistentAngerTarget;

	public Rhinesuchus(EntityType<? extends CarnivoreSemiAquaticMob> entity, Level world) {
		super(entity, world);
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
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
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
			}
		}
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.rhinesuchusHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.rhinesuchusAttackDamage.get());
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new BreathAirGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticFindWaterGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticLeaveWaterGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomStrollGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialReasonableAttackGoal(this, 1.2F));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, DinoTypes.RHINESUCHUS));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, DiictodonEntity.class, this::isHungry));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, Protosuchus.class, this::isHungry));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, Protosuchus.class, this::isAngryAt));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
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
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		return LostWorldsEntities.RHINESUCHUS.create(world);
	}

	@Override
	public ActivityType activity() {
		return ActivityType.DIURNAL;
	}

	@Override
	public int maxHunger() {
		return 8000;
	}

	@Override
	public double getInWaterSpeed() {
		return 0.45D;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		this.addPersistentAngerSaveData(nbt);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		if (!this.level.isClientSide) {
			this.readPersistentAngerSaveData((ServerLevel) this.level, nbt);
		}
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (!this.level.isClientSide) {
			this.updatePersistentAnger((ServerLevel) this.level, true);
		}
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(DATA_REMAINING_ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int anger) {
		this.entityData.set(DATA_REMAINING_ANGER_TIME, anger);
	}

	@Override
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		this.persistentAngerTarget = uuid;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}
}
