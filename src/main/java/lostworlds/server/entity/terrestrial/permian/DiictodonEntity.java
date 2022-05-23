package lostworlds.server.entity.terrestrial.permian;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.goal.terrestrial.SleepGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyAvoidEntityGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookAtPlayerGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.server.entity.goal.terrestrial.SleepySwimGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyWaterAvoidingRandomStrollGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatGrassGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatMossySoilGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialEatPodzolGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.server.entity.goal.terrestrial.entity.DiictodonCreateTerritoryGoal;
import lostworlds.server.entity.goal.terrestrial.entity.DiictodonLayEggGoal;
import lostworlds.server.entity.semiaquatic.permian.Rhinesuchus;
import lostworlds.server.entity.terrestrial.EggLayingMob;
import lostworlds.server.entity.utils.FoodLists;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.CreatureDiet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DiictodonEntity extends EggLayingMob {
	private static final Ingredient FOOD_ITEMS = FoodLists.HERBIVORE;
	private AnimationFactory factory = new AnimationFactory(this);

	public DiictodonEntity(EntityType<? extends DiictodonEntity> entity, Level world) {
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
		return 9000;
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.2F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.diictodonHeath.get());
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomStrollGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatGrassGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatPodzolGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialEatMossySoilGoal(this));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, Dimetrodon.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, Gorgonops.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, Rhinesuchus.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new DiictodonCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new DiictodonLayEggGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
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
		return LostWorldsEntities.DIICTODON.create(world);
	}
}
