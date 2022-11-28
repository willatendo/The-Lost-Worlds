package lostworlds.server.entity.semiaquatic.modern;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.goal.ReasonedAttackableTargetGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticFindWaterGoal;
import lostworlds.server.entity.goal.semiaquatic.SemiAquaticLeaveWaterGoal;
import lostworlds.server.entity.goal.terrestrial.SleepGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyBreedGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookAtPlayerGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyLookRandomlyGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyTemptGoal;
import lostworlds.server.entity.goal.terrestrial.SleepyWaterAvoidingRandomStrollGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialCreateTerritoryGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialGoHomeGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialLayEggGoal;
import lostworlds.server.entity.goal.terrestrial.TerrestrialReasonableAttackGoal;
import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticMob;
import lostworlds.server.entity.utils.FoodLists;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.species.SpeciesType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.TurtleEggBlock;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class GreatAuk extends CarnivoreSemiAquaticMob implements SpeciesTagModelAndTextureable {
	private static final Ingredient FOOD_ITEMS = FoodLists.PISCIVORE;
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public GreatAuk(EntityType<? extends CarnivoreSemiAquaticMob> entity, Level world) {
		super(entity, world);
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return LostWorldsTags.ModSpeciesTypeTags.GREAT_AUK;
	}

	@Override
	public byte getVarientData() {
		return this.getVarient();
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.greatAukHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.greatAukAttackDamage.get());
	}

	public static boolean canGreatAukSpawn(EntityType<GreatAuk> entity, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
		return blockPos.getY() < serverLevelAccessor.getSeaLevel() + 4 && TurtleEggBlock.onSand(serverLevelAccessor, blockPos) && serverLevelAccessor.getRawBrightness(blockPos, 0) > 8;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new BreathAirGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticFindWaterGoal(this));
		this.goalSelector.addGoal(2, new SemiAquaticLeaveWaterGoal(this));
		this.goalSelector.addGoal(1, new SleepyWaterAvoidingRandomStrollGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));
		this.goalSelector.addGoal(2, new SleepyLookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new SleepyLookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new TerrestrialReasonableAttackGoal(this, 1.2F));
		this.goalSelector.addGoal(5, new SleepGoal(this));
		this.goalSelector.addGoal(5, new TerrestrialCreateTerritoryGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new SleepyBreedGoal.Egg(this, 1.0D));
		this.goalSelector.addGoal(6, new TerrestrialLayEggGoal(this, 1.0D, AncientCreatures.GREAT_AUK));
		this.goalSelector.addGoal(9, new TerrestrialGoHomeGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new SleepyTemptGoal(this, 1.0D, false, FOOD_ITEMS));
		this.targetSelector.addGoal(1, new ReasonedAttackableTargetGoal<>(this, Cod.class, this::isHungry));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
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
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		return LostWorldsEntities.GREAT_AUK.create(world);
	}

	@Override
	public ActivityType activity() {
		return ActivityType.DIURNAL;
	}

	@Override
	public int maxHunger() {
		return 9000;
	}

	@Override
	public double getInWaterSpeed() {
		return 0.45D;
	}
}
