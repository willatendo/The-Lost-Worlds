package lostworlds.server.entity.aquatic.permian;

import lostworlds.server.entity.aquatic.BasicFishLikeMob;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Palaeoniscum extends BasicFishLikeMob implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);

	public Palaeoniscum(EntityType<? extends Palaeoniscum> entity, Level world) {
		super(entity, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.palaeoniscum", true));
		return PlayState.CONTINUE;
	}

	@Override
	protected ItemStack getBucketItemStack() {
		return DinoTypes.PALAEONISCUM.getFishBucket().get().getDefaultInstance();
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
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
