package lostworlds.server.entity.illager;

import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
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
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
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
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.GroundPathHelper;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

public class FossilPoacherEntity extends AbstractIllagerEntity {
	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (difficulty) -> {
		return difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;
	};

	public FossilPoacherEntity(EntityType<? extends FossilPoacherEntity> entity, World world) {
		super(entity, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllagerEntity.RaidOpenDoorGoal(this));
		this.goalSelector.addGoal(2, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(4, new SmashPlantFossilGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(4, new SmashSoftStoneGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(4, new SmashSoftDirtGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 15.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@Override
	protected void customServerAiStep() {
		if (!this.isNoAi() && GroundPathHelper.hasGroundPathNavigation(this)) {
			boolean flag = ((ServerWorld) this.level).isRaided(this.blockPosition());
			((GroundPathNavigator) this.getNavigation()).setCanOpenDoors(flag);
		}

		super.customServerAiStep();
	}

	public static AttributeModifierMap createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.FOLLOW_RANGE, 32.0D).build();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.PILLAGER_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PILLAGER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.PILLAGER_HURT;
	}

	@Override
	public AbstractIllagerEntity.ArmPose getArmPose() {
		return this.isAggressive() ? AbstractIllagerEntity.ArmPose.ATTACKING : AbstractIllagerEntity.ArmPose.NEUTRAL;
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) {
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(world, difficulty, reason, data, nbt);
		((GroundPathNavigator) this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(difficulty);
		this.populateDefaultEquipmentEnchantments(difficulty);
		return ilivingentitydata;
	}

	@Override
	public void applyRaidBuffs(int wave, boolean buff) {
		ItemStack itemstack = new ItemStack(LostWorldsItems.HAMMER);
		Raid raid = this.getCurrentRaid();
		int i = 1;
		if (wave > raid.getNumGroups(Difficulty.NORMAL)) {
			i = 2;
		}

		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if (flag) {
			Map<Enchantment, Integer> map = Maps.newHashMap();
			map.put(Enchantments.SHARPNESS, i);
			EnchantmentHelper.setEnchantments(map, itemstack);
		}

		this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		if (this.getCurrentRaid() == null) {
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(LostWorldsItems.HAMMER));
		}
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.PILLAGER_CELEBRATE;
	}

	@Override
	public boolean isAlliedTo(Entity entity) {
		if (super.isAlliedTo(entity)) {
			return true;
		} else if (entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == CreatureAttribute.ILLAGER) {
			return this.getTeam() == null && entity.getTeam() == null;
		} else {
			return false;
		}
	}

	static class FossilPoacherBreakDoorGoal extends BreakDoorGoal {
		public FossilPoacherBreakDoorGoal(MobEntity entity) {
			super(entity, 6, FossilPoacherEntity.DOOR_BREAKING_PREDICATE);
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canContinueToUse() {
			FossilPoacherEntity fossilpoacher = (FossilPoacherEntity) this.mob;
			return fossilpoacher.hasActiveRaid() && super.canContinueToUse();
		}

		public boolean canUse() {
			FossilPoacherEntity fossilpoacher = (FossilPoacherEntity) this.mob;
			return fossilpoacher.hasActiveRaid() && fossilpoacher.random.nextInt(10) == 0 && super.canUse();
		}

		public void start() {
			super.start();
			this.mob.setNoActionTime(0);
		}
	}

	static class SmashPlantFossilGoal extends BreakBlockGoal {
		private final FossilPoacherEntity entity;

		SmashPlantFossilGoal(FossilPoacherEntity entity, double searchRange, int verticalSearchRange) {
			super(LostWorldsBlocks.PLANT_FOSSIL, entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(IWorld world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(World world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class SmashSoftStoneGoal extends BreakBlockGoal {
		private final FossilPoacherEntity entity;

		SmashSoftStoneGoal(FossilPoacherEntity entity, double searchRange, int verticalSearchRange) {
			super(LostWorldsBlocks.SOFT_STONE, entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(IWorld world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(World world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class SmashSoftDirtGoal extends BreakBlockGoal {
		private final FossilPoacherEntity entity;

		SmashSoftDirtGoal(FossilPoacherEntity entity, double searchRange, int verticalSearchRange) {
			super(LostWorldsBlocks.SOFT_DIRT, entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(IWorld world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.GRAVEL_BREAK, SoundCategory.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(World world, BlockPos pos) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.GRAVEL_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class BreakBlockGoal extends MoveToBlockGoal {
		private final Block blockToRemove;
		private final MobEntity removerMob;
		private int ticksSinceReachedGoal;

		public BreakBlockGoal(Block block, CreatureEntity entity, double searchRange, int verticalSearchRange) {
			super(entity, searchRange, 24, verticalSearchRange);
			this.blockToRemove = block;
			this.removerMob = entity;
		}

		@Override
		public boolean canUse() {
			if (!ForgeHooks.canEntityDestroy(this.removerMob.level, this.blockPos, this.removerMob)) {
				return false;
			} else if (this.nextStartTick > 0) {
				--this.nextStartTick;
				return false;
			} else if (this.tryFindBlock()) {
				this.nextStartTick = 20;
				return true;
			} else {
				this.nextStartTick = this.nextStartTick(this.mob);
				return false;
			}
		}

		private boolean tryFindBlock() {
			return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true : this.findNearestBlock();
		}

		@Override
		public void stop() {
			super.stop();
			this.removerMob.fallDistance = 1.0F;
		}

		@Override
		public void start() {
			super.start();
			this.ticksSinceReachedGoal = 0;
		}

		public void playDestroyProgressSound(IWorld world, BlockPos pos) {
		}

		public void playBreakSound(World world, BlockPos pos) {
		}

		@Override
		public void tick() {
			super.tick();
			World world = this.removerMob.level;
			BlockPos blockpos = this.removerMob.blockPosition();
			BlockPos blockpos1 = this.getPosWithBlock(blockpos, world);
			Random random = this.removerMob.getRandom();
			if (this.isReachedTarget() && blockpos1 != null) {
				if (this.ticksSinceReachedGoal > 0) {
					Vector3d vector3d = this.removerMob.getDeltaMovement();
					this.removerMob.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
					if (!world.isClientSide) {
						((ServerWorld) world).sendParticles(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(LostWorldsBlocks.PLANT_FOSSIL)), (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
					}
				}

				if (this.ticksSinceReachedGoal % 2 == 0) {
					Vector3d vector3d1 = this.removerMob.getDeltaMovement();
					this.removerMob.setDeltaMovement(vector3d1.x, -0.3D, vector3d1.z);
					if (this.ticksSinceReachedGoal % 6 == 0) {
						this.playDestroyProgressSound(world, this.blockPos);
					}
				}

				if (this.ticksSinceReachedGoal > 60) {
					world.removeBlock(blockpos1, false);
					if (!world.isClientSide) {
						for (int i = 0; i < 20; ++i) {
							double d3 = random.nextGaussian() * 0.02D;
							double d1 = random.nextGaussian() * 0.02D;
							double d2 = random.nextGaussian() * 0.02D;
							((ServerWorld) world).sendParticles(ParticleTypes.POOF, (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
						}

						this.playBreakSound(world, blockpos1);
					}
				}

				++this.ticksSinceReachedGoal;
			}
		}

		@Nullable
		private BlockPos getPosWithBlock(BlockPos pos, IBlockReader reader) {
			if (reader.getBlockState(pos).is(this.blockToRemove)) {
				return pos;
			} else {
				BlockPos[] ablockpos = new BlockPos[] { pos.below(), pos.west(), pos.east(), pos.north(), pos.south(), pos.below(2) };

				for (BlockPos blockpos : ablockpos) {
					if (reader.getBlockState(blockpos).is(this.blockToRemove)) {
						return blockpos;
					}
				}

				return null;
			}
		}

		@Override
		protected boolean isValidTarget(IWorldReader reader, BlockPos pos) {
			IChunk ichunk = reader.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
			if (ichunk == null) {
				return false;
			} else {
				return ichunk.getBlockState(pos).is(this.blockToRemove) && ichunk.getBlockState(pos.above()).isAir() && ichunk.getBlockState(pos.above(2)).isAir();
			}
		}
	}
}
