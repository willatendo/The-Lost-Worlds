package lostworlds.library.entity.terrestrial.cretaceous;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.goal.NaturalBreedingGoal;
import lostworlds.library.entity.goal.terrestrial.PanicGoal;
import lostworlds.library.entity.goal.terrestrial.SleepGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyAvoidEntityGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookAtGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.terrestrial.SleepySwimGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialEatGrassGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialEatMossySoilGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialEatPodzolGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.library.entity.terrestrial.EggLayingEntity;
import lostworlds.library.entity.utils.FoodLists;
import lostworlds.library.entity.utils.IHerdPanic;
import lostworlds.library.entity.utils.enums.ActivityType;
import lostworlds.library.entity.utils.enums.CreatureDiet;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class ZephyrosaurusEntity extends EggLayingEntity implements IHerdPanic {
	private static final Ingredient FOOD_ITEMS = FoodLists.HERBIVORE;
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	public ZephyrosaurusEntity(EntityType<? extends ZephyrosaurusEntity> entity, World world) {
		super(entity, world);
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
		return 10000;
	}

	public static AttributeModifierMap createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.SERVER_CONFIG.chilesaurusHeath.get()).build();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatGrassGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatPodzolGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatMossySoilGoal(this));
		this.goalSelector.addGoal(5, new PanicGoal(this, 1.0F));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, UtahraptorEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, FukuivenatorEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, CarnotaurusEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, GiganotosaurusEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new NaturalBreedingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, DinoTypes.CHILESAURUS));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
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
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		return EntityInit.ZEPHYROSAURUS.create(world);
	}

	@Override
	public void onPanic() {		
	}

	@Override
	public boolean canPanic() {
		return true;
	}
}
