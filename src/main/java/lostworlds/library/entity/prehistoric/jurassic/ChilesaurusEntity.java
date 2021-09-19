package lostworlds.library.entity.prehistoric.jurassic;

import lostworlds.content.client.entity.model.ChilesaurusModel;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.entity.goal.HerbivoreEatGrassGoal;
import lostworlds.library.entity.prehistoric.HerbivoreEntity;
import lostworlds.library.item.block.SeedItem;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChilesaurusEntity extends HerbivoreEntity
{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.CALAMITES_SUCKOWII, BlockInit.DILLHOFFIA, BlockInit.DUISBERGIA, BlockInit.GROUND_FERNS, BlockInit.OSMUNDA, BlockInit.PERMIAN_DESERT_FERNS, BlockInit.PERMIAN_DESERT_SHRUB, BlockInit.WILLIAMSONIA, BlockInit.ZAMITES);
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) 
	{
		if(event.isMoving())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chilesaurus.walk", true));
			return PlayState.CONTINUE;
		}
		else if(this.entityData.get(this.EATING))
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chilesaurus.eat", false));
			return PlayState.CONTINUE;
		}
		else
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chilesaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public ChilesaurusEntity(EntityType<? extends ChilesaurusEntity> entity, World world) 
	{
		super(entity, world, TimeEras.JURASSIC_PERIOD);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.chilesaurusHeath.get()).build();
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		byte pattern = (byte) random.nextInt(ChilesaurusModel.textures.getFirst().size());
		this.entityData.define(PATTERN, pattern);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new HerbivoreEatGrassGoal(this));
		this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
	}

	@Override
	public void registerControllers(AnimationData data) 
	{
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() 
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
