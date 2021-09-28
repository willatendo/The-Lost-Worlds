package lostworlds.library.entity.fossil;

import lostworlds.library.item.ChiselItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
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
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) 
	{
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skeleton.living", true));
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
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
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
		return false;
	}
	
//	@Override
//	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) 
//	{
//		if(this.isLooking())
//		{
//			this.setLooking(false);
//		}
//		if(!this.isLooking())
//		{
//			this.setLooking(true);
//		}
//		return ActionResultType.SUCCESS;
//	}
	
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

	public void onKillCommand() 
	{
		this.remove();
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return null;
	}
	
	static class LookAtPlayerGoal extends LookAtGoal 
	{
		FossilEntity entity;
		
		public LookAtPlayerGoal(FossilEntity entity, Class<? extends LivingEntity> watchTargetClass, float maxDistance) 
		{
			super(entity, watchTargetClass, maxDistance);
			this.entity = entity;
		}
		
		@Override
		public boolean canUse() 
		{
			if(entity.isLooking()) 
			{
				return super.canUse();
			} 
			else 
			{
				return false;
			}
		}
		
		@Override
		public boolean canContinueToUse() 
		{
			return super.canContinueToUse() && entity.isLooking();
		}
	}
}
