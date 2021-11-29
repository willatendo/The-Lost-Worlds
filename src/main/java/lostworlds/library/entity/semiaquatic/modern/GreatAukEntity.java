package lostworlds.library.entity.semiaquatic.modern;

import java.util.Random;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticFindWaterGoal;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticLeaveWaterGoal;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticSwimGoal;
import lostworlds.library.entity.goal.semiaquatic.SemiAquaticTemptGoal;
import lostworlds.library.entity.semiaquatic.BreedingSemiAquaticEntity;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class GreatAukEntity extends BreedingSemiAquaticEntity implements ITyrannomatable
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		if(event.isMoving())
		{
			if(this.isInWaterOrBubble())
			{
				event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.great_auk.swim", true));
				return PlayState.CONTINUE;
			}
			else
			{
				event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.great_auk.walk", true));
				return PlayState.CONTINUE;
			}
		}
		else
		{
			if(this.isInWaterOrBubble())
			{
				event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.great_auk.idle_water", true));
				return PlayState.CONTINUE;
			}
			else 
			{
				event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.great_auk.idle", true));
				return PlayState.CONTINUE;
				
			}
		}
	}
	
	public GreatAukEntity(EntityType<? extends BreedingSemiAquaticEntity> entity, World world) 
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
		this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0F));
		this.goalSelector.addGoal(3, new SemiAquaticSwimGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(6, new SemiAquaticTemptGoal(this, 1.0F, FOOD_ITEMS));
		this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.2F, false));
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
}
