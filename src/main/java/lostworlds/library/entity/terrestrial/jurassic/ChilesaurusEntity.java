package lostworlds.library.entity.terrestrial.jurassic;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.goal.herbivore.HerbivoreEatGrassGoal;
import lostworlds.library.entity.goal.herbivore.HerbivoreEatMossySoilGoal;
import lostworlds.library.entity.goal.herbivore.HerbivoreEatPodzolGoal;
import lostworlds.library.entity.goal.herbivore.NaturalBreedingGoal;
import lostworlds.library.entity.goal.herbivore.SleepGoal;
import lostworlds.library.entity.goal.herbivore.SleepyAvoidEntityGoal;
import lostworlds.library.entity.goal.herbivore.SleepyBreedGoal;
import lostworlds.library.entity.goal.herbivore.SleepyLookAtGoal;
import lostworlds.library.entity.goal.herbivore.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.herbivore.SleepySwimGoal;
import lostworlds.library.entity.goal.herbivore.SleepyTemptGoal;
import lostworlds.library.entity.goal.herbivore.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
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

public class ChilesaurusEntity extends HerbivoreEntity
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.CALAMITES_SUCKOWII, BlockInit.DILLHOFFIA, BlockInit.DUISBERGIA, BlockInit.GROUND_FERNS, BlockInit.OSMUNDA, BlockInit.PERMIAN_DESERT_FERNS, BlockInit.PERMIAN_DESERT_SHRUB, BlockInit.WILLIAMSONIA, BlockInit.ZAMITES);
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		if(event.isMoving())
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.chilesaurus.walk", true));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.EATING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.chilesaurus.eat", false));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.SLEEPING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.chilesaurus.into_sleep", false).addAnimation("animation.chilesaurus.sleeping", false).addAnimation("animation.chilesaurus.out_of_sleep", false));
			return PlayState.CONTINUE;
		}
		else
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.chilesaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public ChilesaurusEntity(EntityType<? extends ChilesaurusEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.chilesaurusHeath.get()).build();
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatGrassGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatPodzolGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatMossySoilGoal(this));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new SleepyAvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(6, new NaturalBreedingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new SleepyBreedGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
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
		return EntityInit.CHILESAURUS.create(world);
	}	
}
