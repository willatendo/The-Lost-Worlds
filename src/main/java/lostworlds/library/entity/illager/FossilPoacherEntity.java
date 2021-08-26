package lostworlds.library.entity.illager;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import lostworlds.content.server.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.GroundPathHelper;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;

public class FossilPoacherEntity extends AbstractIllagerEntity
{
	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (difficulty) -> 
	{
		return difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;
	};
	
	public FossilPoacherEntity(EntityType<? extends FossilPoacherEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllagerEntity.RaidOpenDoorGoal(this));
		this.goalSelector.addGoal(2, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 15.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}
	
	@Override
	protected void customServerAiStep() 
	{
		if(!this.isNoAi() && GroundPathHelper.hasGroundPathNavigation(this)) 
		{
			boolean flag = ((ServerWorld)this.level).isRaided(this.blockPosition());
			((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(flag);
		}
		
		super.customServerAiStep();
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.FOLLOW_RANGE, 32.0D).build();
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return SoundEvents.PILLAGER_AMBIENT;
	}
	
	@Override
	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.PILLAGER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) 
	{
		return SoundEvents.PILLAGER_HURT;
	}
	
	@Override
	public AbstractIllagerEntity.ArmPose getArmPose() 
	{
		return this.isAggressive() ? AbstractIllagerEntity.ArmPose.ATTACKING : AbstractIllagerEntity.ArmPose.NEUTRAL;
	}
	
	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) 
	{
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(world, difficulty, reason, data, nbt);
		((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(difficulty);
		this.populateDefaultEquipmentEnchantments(difficulty);
		return ilivingentitydata;
	}

	@Override
	public void applyRaidBuffs(int wave, boolean buff) 
	{
		ItemStack itemstack = new ItemStack(ItemInit.HAMMER);
		Raid raid = this.getCurrentRaid();
		int i = 1;
		if(wave > raid.getNumGroups(Difficulty.NORMAL)) 
		{
			i = 2;
		}
		
		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if(flag) 
		{
			Map<Enchantment, Integer> map = Maps.newHashMap();
			map.put(Enchantments.SHARPNESS, i);
			EnchantmentHelper.setEnchantments(map, itemstack);
		}
		
		this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) 
	{
		if(this.getCurrentRaid() == null) 
		{
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.HAMMER));
		}
	}

	@Override
	public SoundEvent getCelebrateSound() 
	{
		return SoundEvents.PILLAGER_CELEBRATE;
	}

	@Override
	public boolean isAlliedTo(Entity entity) 
	{
		if(super.isAlliedTo(entity)) 
		{
			return true;
		} 
		else if(entity instanceof LivingEntity && ((LivingEntity)entity).getMobType() == CreatureAttribute.ILLAGER) 
		{
			return this.getTeam() == null && entity.getTeam() == null;
		} 
		else 
		{
			return false;
		}
	}
	
	static class FossilPoacherBreakDoorGoal extends BreakDoorGoal 
	{
		public FossilPoacherBreakDoorGoal(MobEntity entity) 
		{
			super(entity, 6, FossilPoacherEntity.DOOR_BREAKING_PREDICATE);
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		public boolean canContinueToUse() 
		{
			FossilPoacherEntity fossilpoacher = (FossilPoacherEntity)this.mob;
			return fossilpoacher.hasActiveRaid() && super.canContinueToUse();
		}
		
		public boolean canUse() 
		{
			FossilPoacherEntity fossilpoacher = (FossilPoacherEntity)this.mob;
			return fossilpoacher.hasActiveRaid() && fossilpoacher.random.nextInt(10) == 0 && super.canUse();
		}
		
		public void start() 
		{
			super.start();
			this.mob.setNoActionTime(0);
		}
	}
}
