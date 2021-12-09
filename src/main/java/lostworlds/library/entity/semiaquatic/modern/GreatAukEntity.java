package lostworlds.library.entity.semiaquatic.modern;

import java.util.Random;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.goal.NaturalBreedingGoal;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticFindWaterGoal;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticLeaveWaterGoal;
import lostworlds.library.entity.goal.terrestrial.SleepGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookAtGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.library.entity.goal.terrestrial.SleepyWaterAvoidingRandomWalkingGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.library.entity.goal.terrestrial.TerrestrialReasonableAttackGoal;
import lostworlds.library.entity.semiaquatic.CarnivoreSemiAquaticEntity;
import lostworlds.library.entity.utils.FoodLists;
import lostworlds.library.entity.utils.enums.ActivityType;
import lostworlds.library.entity.utils.enums.DinoTypes;
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
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class GreatAukEntity extends CarnivoreSemiAquaticEntity
{
	private static final Ingredient FOOD_ITEMS = FoodLists.PISCIVORE;
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	public GreatAukEntity(EntityType<? extends CarnivoreSemiAquaticEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.greatAukHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.greatAukAttackDamage.get()).build();
	}
	
	public static boolean checkSpawnRules(EntityType<GreatAukEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) 
	{
		return pos.getY() < world.getSeaLevel() + 4 && TurtleEggBlock.onSand(world, pos) && world.getRawBrightness(pos, 0) > 8;
	}
	
	@Override
	protected void registerGoals() 
	{
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
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, DinoTypes.GREAT_AUK));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CodEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, true));
	}
	
	@Override
	public boolean isFood(ItemStack stack) 
	{
		return FOOD_ITEMS.test(stack);
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
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return EntityInit.GREAT_AUK.create(world);
	}

	@Override
	public ActivityType activity() 
	{
		return ActivityType.DIURNAL;
	}

	@Override
	public int maxHunger() 
	{
		return 9000;
	}
	
	@Override
	public double getInWaterSpeed() 
	{
		return 0.45D;
	}
}
