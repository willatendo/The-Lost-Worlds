package lostworlds.server.entity.aquatic.modern;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.aquatic.BasicFishLikeMob;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Nautilus extends BasicFishLikeMob implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);

	public Nautilus(EntityType<? extends PathfinderMob> entity, Level world) {
		super(entity, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.nautilus", true));
		return PlayState.CONTINUE;
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.nautilusHeath.get()).add(Attributes.ARMOR, LostWorldsConfig.COMMON_CONFIG.nautilusArmour.get());
	}

	@Override
	protected ItemStack getBucketItemStack() {
		return DinoTypes.NAUTILUS.getFishBucket().get().getDefaultInstance();
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.BONE_BLOCK_BREAK;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ITEM_BREAK;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}
