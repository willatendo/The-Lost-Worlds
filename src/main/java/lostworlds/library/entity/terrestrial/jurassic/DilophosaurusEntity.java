package lostworlds.library.entity.terrestrial.jurassic;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.Size;
import lostworlds.library.entity.goal.NaturalBreedingGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.CarnivoreSleepGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepyBreedGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepyLookAtGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepySwimGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepyTemptGoal;
import lostworlds.library.entity.goal.terrestrial.carnivore.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import lostworlds.library.item.block.SeedItem;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class DilophosaurusEntity extends CarnivoreEntity
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.BEEF);
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		if(event.isMoving())
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.dilophosaurus.walk", true));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.EATING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.dilophosaurus.eat", false));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.SLEEPING))
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.dilophosaurus.into_sleep", false).addAnimation("animation.dilophosaurus.sleeping", false).addAnimation("animation.dilophosaurus.out_of_sleep", false));
			return PlayState.CONTINUE;
		}
		else
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.dilophosaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}
	
	public DilophosaurusEntity(EntityType<? extends CarnivoreEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.dilophosaurusHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.dilophosaurusAttackDamage.get()).build();
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new CarnivoreSleepGoal(this));
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
	public Size entitySize() 
	{
		return Size.MEDIUM;
	}	

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return EntityInit.DILOPHOSAURUS.create(world);
	}
}
