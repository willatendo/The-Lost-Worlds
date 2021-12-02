package lostworlds.library.entity.terrestrial.cretaceous;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.goal.NaturalBreedingGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreCreateTerritoryGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreEatGrassGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreEatMossySoilGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreEatPodzolGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreGoHomeGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreLayEggGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.HerbivoreSleepGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyAvoidEntityGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyBreedGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyLookAtGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepySwimGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyTemptGoal;
import lostworlds.library.entity.goal.terrestrial.herbivore.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import lostworlds.library.item.block.SeedItem;
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
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class PsittacosaurusEntity extends HerbivoreEggLayingEntity
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.CALAMITES_SUCKOWII, BlockInit.DILLHOFFIA, BlockInit.DUISBERGIA, BlockInit.GROUND_FERNS, BlockInit.OSMUNDA, BlockInit.PERMIAN_DESERT_FERNS, BlockInit.PERMIAN_DESERT_SHRUB, BlockInit.WILLIAMSONIA, BlockInit.ZAMITES);
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		if(event.isMoving())
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.psittacosaurus.walk", true));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.EATING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.psittacosaurus.eat", false));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.SLEEPING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.psittacosaurus.into_sleep", false).addAnimation("animation.psittacosaurus.sleeping", false).addAnimation("animation.psittacosaurus.out_of_sleep", false));
			return PlayState.CONTINUE;
		}
		else
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.psittacosaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public PsittacosaurusEntity(EntityType<? extends PsittacosaurusEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.psittacosaurusHeath.get()).build();
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatGrassGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatPodzolGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatMossySoilGoal(this));
		this.goalSelector.addGoal(5, new HerbivoreSleepGoal(this));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new HerbivoreCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new NaturalBreedingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new HerbivoreLayEggGoal(this, 1.0D, DinoTypes.PSITTACOSAURUS));
		this.goalSelector.addGoal(9, new HerbivoreGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
	}

	@Override
	public void registerControllers(TyrannomationData data) 
	{
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() 
	{
		return this.factory;
	}
	
	@Override
	public boolean isFood(ItemStack stack) 
	{
		return FOOD_ITEMS.test(stack) || stack.getItem() instanceof SeedItem;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return EntityInit.PSITTACOSAURUS.create(world);
	}
}

