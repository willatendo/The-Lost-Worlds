package lostworlds.server.entity.illager;

import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.ForgeHooks;

public class FossilPoacherEntity extends AbstractIllager {
	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (difficulty) -> {
		return difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;
	};

	public FossilPoacherEntity(EntityType<? extends FossilPoacherEntity> entity, Level world) {
		super(entity, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(2, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(4, new SmashPlantFossilGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(4, new SmashSoftStoneGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(4, new SmashSoftDirtGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	@Override
	protected void customServerAiStep() {
		if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
			boolean flag = ((ServerLevel) this.level).isRaided(this.blockPosition());
			((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(flag);
		}

		super.customServerAiStep();
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return LostWorldsSounds.FOSSIL_POACHER_GRUNT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return LostWorldsSounds.FOSSIL_POACHER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return LostWorldsSounds.FOSSIL_POACHER_HURT.get();
	}

	@Override
	public AbstractIllager.IllagerArmPose getArmPose() {
		return this.isAggressive() ? AbstractIllager.IllagerArmPose.ATTACKING : AbstractIllager.IllagerArmPose.NEUTRAL;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
		SpawnGroupData ilivingentitydata = super.finalizeSpawn(world, difficulty, reason, data, nbt);
		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(difficulty);
		this.populateDefaultEquipmentEnchantments(difficulty);
		return ilivingentitydata;
	}

	@Override
	public void applyRaidBuffs(int wave, boolean buff) {
		ItemStack itemstack = LostWorldsItems.HAMMER.get().getDefaultInstance();
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

		this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		if (this.getCurrentRaid() == null) {
			this.setItemSlot(EquipmentSlot.MAINHAND, LostWorldsItems.HAMMER.get().getDefaultInstance());
		}
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return LostWorldsSounds.FOSSIL_POACHER_CELEBRATE.get();
	}

	@Override
	public boolean isAlliedTo(Entity entity) {
		if (super.isAlliedTo(entity)) {
			return true;
		} else if (entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == MobType.ILLAGER) {
			return this.getTeam() == null && entity.getTeam() == null;
		} else {
			return false;
		}
	}

	static class FossilPoacherBreakDoorGoal extends BreakDoorGoal {
		public FossilPoacherBreakDoorGoal(Mob entity) {
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
			super(LostWorldsBlocks.PLANT_FOSSIL.get(), entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(LevelAccessor world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(Level world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class SmashSoftStoneGoal extends BreakBlockGoal {
		private final FossilPoacherEntity entity;

		SmashSoftStoneGoal(FossilPoacherEntity entity, double searchRange, int verticalSearchRange) {
			super(LostWorldsBlocks.SOFT_STONE.get(), entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(LevelAccessor world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(Level world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class SmashSoftDirtGoal extends BreakBlockGoal {
		private final FossilPoacherEntity entity;

		SmashSoftDirtGoal(FossilPoacherEntity entity, double searchRange, int verticalSearchRange) {
			super(LostWorldsBlocks.SOFT_DIRT.get(), entity, searchRange, verticalSearchRange);
			this.entity = entity;
		}

		@Override
		public void playDestroyProgressSound(LevelAccessor world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.GRAVEL_BREAK, SoundSource.HOSTILE, 0.5F, 0.9F + entity.random.nextFloat() * 0.2F);
		}

		@Override
		public void playBreakSound(Level world, BlockPos pos) {
			world.playSound((Player) null, pos, SoundEvents.GRAVEL_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		}

		@Override
		public double acceptedDistance() {
			return 1.14D;
		}
	}

	static class BreakBlockGoal extends MoveToBlockGoal {
		private final Block blockToRemove;
		private final Mob removerMob;
		private int ticksSinceReachedGoal;

		public BreakBlockGoal(Block block, PathfinderMob entity, double searchRange, int verticalSearchRange) {
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

		public void playDestroyProgressSound(LevelAccessor world, BlockPos pos) {
		}

		public void playBreakSound(Level world, BlockPos pos) {
		}

		@Override
		public void tick() {
			super.tick();
			Level world = this.removerMob.level;
			BlockPos blockpos = this.removerMob.blockPosition();
			BlockPos blockpos1 = this.getPosWithBlock(blockpos, world);
			Random random = this.removerMob.getRandom();
			if (this.isReachedTarget() && blockpos1 != null) {
				if (this.ticksSinceReachedGoal > 0) {
					Vec3 vector3d = this.removerMob.getDeltaMovement();
					this.removerMob.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
					if (!world.isClientSide) {
						((ServerLevel) world).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, LostWorldsBlocks.PLANT_FOSSIL.asStack()), (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
					}
				}

				if (this.ticksSinceReachedGoal % 2 == 0) {
					Vec3 vector3d1 = this.removerMob.getDeltaMovement();
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
							((ServerLevel) world).sendParticles(ParticleTypes.POOF, (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
						}

						this.playBreakSound(world, blockpos1);
					}
				}

				++this.ticksSinceReachedGoal;
			}
		}

		@Nullable
		private BlockPos getPosWithBlock(BlockPos pos, BlockGetter reader) {
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
		protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
			ChunkAccess ichunk = reader.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
			if (ichunk == null) {
				return false;
			} else {
				return ichunk.getBlockState(pos).is(this.blockToRemove) && ichunk.getBlockState(pos.above()).isAir() && ichunk.getBlockState(pos.above(2)).isAir();
			}
		}
	}
}
