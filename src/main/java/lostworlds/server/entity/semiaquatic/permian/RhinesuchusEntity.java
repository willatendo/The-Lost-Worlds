package lostworlds.server.entity.semiaquatic.permian;

import java.util.Random;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.goal.NaturalBreedingGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticFindWaterGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticLeaveWaterGoal;
import lostworlds.server.entity.goal.terrestrial.SleepGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookAtGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialReasonableAttackGoal;
import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticEntity;
import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import lostworlds.server.entity.utils.FoodLists;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.util.IngredientUtil;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class RhinesuchusEntity extends CarnivoreSemiAquaticEntity {
	public static final DataParameter<Integer> MOISTNESS_LEVEL = EntityDataManager.defineId(RhinesuchusEntity.class, DataSerializers.INT);
	private static final Ingredient FOOD_ITEMS = IngredientUtil.combine(FoodLists.CARNIVORE, FoodLists.PISCIVORE);
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	public RhinesuchusEntity(EntityType<? extends CarnivoreSemiAquaticEntity> entity, World world) {
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

	public static AttributeModifierMap createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.SERVER_CONFIG.rhinesuchusHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.SERVER_CONFIG.rhinesuchusAttackDamage.get()).build();
	}

	public static boolean checkSpawnRules(EntityType<RhinesuchusEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
		return pos.getY() < world.getSeaLevel() + 4 && TurtleEggBlock.onSand(world, pos) && world.getRawBrightness(pos, 0) > 8;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new BreatheAirGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticFindWaterGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticLeaveWaterGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialReasonableAttackGoal(this, 1.2F));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new NaturalBreedingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, DinoTypes.RHINESUCHUS));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, DiictodonEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ProtosuchusEntity.class, true));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public void registerControllers(TyrannomationData data) {
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() {
		return this.factory;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		return LostWorldsEntities.PROTOSUCHUS.create(world);
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
}
