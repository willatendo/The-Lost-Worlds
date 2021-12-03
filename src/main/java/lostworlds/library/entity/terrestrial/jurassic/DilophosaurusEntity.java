package lostworlds.library.entity.terrestrial.jurassic;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.goal.NaturalBreedingGoal;
import lostworlds.library.entity.goal.terrestrial.SleepGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookAtGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.terrestrial.SleepySwimGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyWaterAvoidingRandomWalkingGoal;
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
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class DilophosaurusEntity extends CarnivoreEntity
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.BEEF);
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	public DilophosaurusEntity(EntityType<? extends DilophosaurusEntity> entity, World world) 
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
		this.goalSelector.addGoal(5, new SleepGoal(this));
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
		return EntityInit.DILOPHOSAURUS.create(world);
	}
}
