package lostworlds.library.entity.terrestrial.cretaceous;

import java.util.UUID;

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
import lostworlds.library.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialReasonableAttackGoal;
import lostworlds.library.entity.terrestrial.CarnivoreEntity;
import lostworlds.library.entity.utils.FoodLists;
import lostworlds.library.entity.utils.IReasonableAngerable;
import lostworlds.library.entity.utils.enums.ActivityType;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class CarnotaurusEntity extends CarnivoreEntity implements IReasonableAngerable
{
	private static final Ingredient FOOD_ITEMS = FoodLists.CARNIVORE;
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
	private int remainingPersistentAngerTime;
	private UUID persistentAngerTarget;
	
	public CarnotaurusEntity(EntityType<? extends CarnotaurusEntity> entity, World world) 
	{
		super(entity, world);
	}

	@Override
	public int maxHunger() 
	{
		return 70000;
	}
	
	@Override
	public ActivityType activity() 
	{
		return ActivityType.DIURNAL;
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.carnotaurusHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.carnotaurusAttackDamage.get()).build();
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SleepySwimGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomWalkingGoal.Egg(this, 1.0D));	
		this.goalSelector.addGoal(2, new SleepyLookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialReasonableAttackGoal(this, 1.8F));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new NaturalBreedingGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, DinoTypes.CARNOTAURUS));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, PlayerEntity.class, this::isHungry));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, PlayerEntity.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, GiganotosaurusEntity.class, this::isAngryAt));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, TyrannosaurusEntity.class, this::isAngryAt));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, UtahraptorEntity.class, this::isHungry));
		this.targetSelector.addGoal(3, new ReasonedAttackableTargetGoal<>(this, UtahraptorEntity.class, this::isAngryAt));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, FukuivenatorEntity.class, this::isHungry));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, PsittacosaurusEntity.class, this::isHungry));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, ZephyrosaurusEntity.class, this::isHungry));
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
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return EntityInit.CARNOTAURUS.create(world);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		this.addPersistentAngerSaveData(nbt);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		if(level.isClientSide) 
		{
			this.readPersistentAngerSaveData((ServerWorld)this.level, nbt);
		}
	}
	
	@Override
	public void aiStep() 
	{
		super.aiStep();
		
		if(!this.level.isClientSide) 
		{
			this.updatePersistentAnger((ServerWorld) this.level, true);
		}
	}

	@Override
	public void startPersistentAngerTimer() 
	{
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
	}

	@Override
	public void setRemainingPersistentAngerTime(int anger) 
	{
		this.remainingPersistentAngerTime = anger;
	}

	@Override
	public int getRemainingPersistentAngerTime() 
	{
		return this.remainingPersistentAngerTime;
	}

	@Override
	public void setPersistentAngerTarget(UUID target) 
	{
		this.persistentAngerTarget = target;
	}

	@Override
	public UUID getPersistentAngerTarget() 
	{
		return this.persistentAngerTarget;
	}
}

