package lostworlds.library.entity.fossil;

import java.util.EnumSet;

import lostworlds.library.item.ChiselItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FossilEntity extends AnimalEntity implements IAnimatable
{
	private static final DataParameter<Boolean> PUSHING = EntityDataManager.defineId(FossilEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LOOKING = EntityDataManager.defineId(FossilEntity.class, DataSerializers.BOOLEAN);
	
	public static final String animation = "animation.skeleton.living";
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) 
	{
		event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animation, true));
		return PlayState.CONTINUE;
	}
	
	public FossilEntity(EntityType<? extends FossilEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100).build();
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
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this));
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(PUSHING, false);
		this.entityData.define(LOOKING, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("IsPushable", this.isPushable());
		nbt.putBoolean("IsLooking", this.isLooking());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		this.setPushable(nbt.getBoolean("IsPushable"));
		this.setLooking(nbt.getBoolean("IsLooking"));
	}

	@Override
	public boolean isPushable() 
	{
		return this.canBePushed();
	}
	
	@Override
	protected void doPush(Entity entity) { }
	
	public boolean canBePushed() 
	{
		return this.entityData.get(PUSHING);
	}

	public void setPushable(boolean isPushable) 
	{
		this.entityData.set(PUSHING, isPushable);
	}
	
	public boolean isLooking() 
	{
		return this.entityData.get(LOOKING);
	}

	public void setLooking(boolean isLooking) 
	{
		this.entityData.set(LOOKING, isLooking);
	}

	@Override
	protected int getExperienceReward(PlayerEntity player) 
	{
		return 0;
	}

	@Override
	public boolean canBreatheUnderwater() 
	{
		return true;
	}
	
	private void playBrokenSound() 
	{
		this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.SKELETON_DEATH, SoundCategory.BLOCKS, 1.0F, 1.0F);

	}

	private void playParticles() 
	{
		if(this.level instanceof ServerWorld) 
		{
			((ServerWorld)this.level).addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.BONE_BLOCK.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double)(this.getBbWidth() / 4.0F), (double)(this.getBbHeight() / 4.0F));
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) 
	{
		if(source.getDirectEntity() instanceof PlayerEntity) 
		{
			PlayerEntity player = (PlayerEntity) source.getDirectEntity();
			if(player.getMainHandItem().getItem() instanceof ChiselItem)
			{
				ItemStack stack = player.getMainHandItem();
				
				stack.hurtAndBreak(1, player, (playerentity) -> 
				{
					playerentity.broadcastBreakEvent(player.getUsedItemHand());
				});
				this.remove();
				this.playBrokenSound();
				this.playParticles();
			}
		}
		return false;
	}
	
	@Override
	public boolean isAffectedByPotions() 
	{
		return false;
	}

	@Override
	public boolean isPushedByFluid() 
	{
		return false;	
	}
	
	public void onKillCommand() 
	{
		this.remove();
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return null;
	}
	
	static class LookAtPlayerGoal extends Goal 
	{
		protected final FossilEntity entity;
		protected Entity lookAt;
		protected final float range;
		private int lookTime;
		protected final float probability;
		protected final Class<? extends LivingEntity> lookAtType;
		protected final EntityPredicate lookAtContext;

		public LookAtPlayerGoal(FossilEntity entity) 
		{
			this(entity, PlayerEntity.class, 32.0F, 0.02F);
		}

		public LookAtPlayerGoal(FossilEntity entity, Class<? extends LivingEntity> lookAtEntity, float range, float probability) 
		{
			this.entity = entity;
			this.lookAtType = lookAtEntity;
			this.range = range;
			this.probability = probability;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
			if(lookAtEntity == PlayerEntity.class) 
			{
				this.lookAtContext = (new EntityPredicate()).range((double) range).allowSameTeam().allowInvulnerable().allowNonAttackable().selector((livingentity) -> 
				{
					return EntityPredicates.notRiding(entity).test(livingentity);
				});
			} 
			else 
			{
				this.lookAtContext = (new EntityPredicate()).range((double) range).allowSameTeam().allowInvulnerable().allowNonAttackable();
			}

		}

		@Override
		public boolean canUse() 
		{
			if(entity.isLooking())
			{
				if(this.entity.getTarget() != null) 
				{
					this.lookAt = this.entity.getTarget();
				}

				if(this.lookAtType == PlayerEntity.class) 
				{
					this.lookAt = this.entity.level.getNearestPlayer(this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
				} 
				else 
				{
					this.lookAt = this.entity.level.getNearestLoadedEntity(this.lookAtType, this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ(), this.entity.getBoundingBox().inflate((double) this.range, 3.0D, (double) this.range));
				}

				return this.lookAt != null;
			}
			else
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse() 
		{
			if(!this.lookAt.isAlive()) 
			{
				return false;
			} 
			else if(this.entity.distanceToSqr(this.lookAt) > (double) (this.range * this.range)) 
			{
				return false;
			} 
			else 
			{
				return this.lookTime > 0 && entity.isLooking();
			}
		}

		@Override
		public void start() 
		{
			this.lookTime = 40 + this.entity.getRandom().nextInt(40);
		}

		@Override
		public void stop() 
		{
			this.lookAt = null;
		}

		@Override
		public void tick() 
		{
			this.entity.getLookControl().setLookAt(this.lookAt.getX(), this.lookAt.getEyeY(), this.lookAt.getZ());
			--this.lookTime;
		}
	}
}
