package lostworlds.server.entity.terrestrial.jurassic;

import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.goal.ReasonedAttackableTargetGoal;
import lostworlds.server.entity.goal.terrestrial.SleepGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyAvoidEntityGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookAtPlayerGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.server.entity.goal.terrestrial.SleepySwimGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyWaterAvoidingRandomStrollGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatGrassGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatMossySoilGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatPodzolGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.server.entity.terrestrial.EggLayingMob;
import lostworlds.server.entity.utils.FoodLists;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.entity.utils.enums.CreatureDiet;
import lostworlds.server.species.SpeciesType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Liaoningosaurus extends EggLayingMob implements NeutralMob, SpeciesTagModelAndTextureable {
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Liaoningosaurus.class, EntityDataSerializers.INT);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private static final Ingredient FOOD_ITEMS = FoodLists.HERBIVORE;
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	private UUID persistentAngerTarget;

	public Liaoningosaurus(EntityType<? extends Liaoningosaurus> entity, Level world) {
		super(entity, world);
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return LostWorldsTags.ModSpeciesTypeTags.LIAONINGOSAURUS;
	}

	@Override
	public byte getVarientData() {
		return this.getVarient();
	}

	@Override
	public CreatureDiet diet() {
		return CreatureDiet.HERBIVORE;
	}

	@Override
	public ActivityType activity() {
		return ActivityType.DIURNAL;
	}

	@Override
	public int maxHunger() {
		return 30000;
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.liaoningosaurusHeath.get());
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomStrollGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatGrassGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatPodzolGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatMossySoilGoal(this));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, AncientCreatures.LIAONINGOSAURUS));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, Player.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, Allosaurus.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, Cryolophosaurus.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, Dilophosaurus.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, Dilophosaurus.class, this::isAngryAt));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
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
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		return LostWorldsEntities.LIAONINGOSAURUS.create(world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
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
