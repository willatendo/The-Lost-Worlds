package lostworlds.server.entity.avian;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public abstract class Pterosaur extends EggLayingMob implements IAnimatable {
	public static final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
	public static final AnimationBuilder SWIM_ANIMATION = new AnimationBuilder().addAnimation("into_swim").addAnimation("swim", true).addAnimation("out_of_swim");
	public static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
	public static final AnimationBuilder FLY_ANIMATION = new AnimationBuilder().addAnimation("into_fly").addAnimation("fly", true).addAnimation("out_of_fly");
	public static final AnimationBuilder SLEEP_ANIMATION = new AnimationBuilder().addAnimation("into_sleep").addAnimation("sleep", true).addAnimation("out_of_sleep");
	public static final AnimationBuilder EAT_ANIMATION = new AnimationBuilder().addAnimation("eat", false);

	public boolean canCompleteFlyingGoals;

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		float limbSwingAmount = event.getLimbSwingAmount();
		boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);
		AnimationController controller = event.getController();

		byte currentAnimation = this.getAnimation();
		switch (currentAnimation) {
		case ANIMATION_SLEEP:
			controller.setAnimation(SLEEP_ANIMATION);
			break;
		case ANIMATION_EAT:
			controller.setAnimation(EAT_ANIMATION);
			break;
		case ANIMATION_GLIDE:
			controller.setAnimation(GLIDE_ANIMATION);
			break;
		case ANIMATION_WALL_WALK:
			controller.setAnimation(WALL_WALK_ANIMATION);
			break;
		case ANIMATION_FLY:
			controller.setAnimation(FLY_ANIMATION);
			break;
		default:
			if (this.isInWaterOrBubble()) {
				controller.setAnimation(SWIM_ANIMATION);
			} else {
				controller.setAnimation(isMoving ? WALK_ANIMATION : IDLE_ANIMATION);
			}
			break;
		}

		return PlayState.CONTINUE;
	}

	public Pterosaur(EntityType<? extends Pterosaur> entityType, Level level) {
		super(entityType, level);
	}
}
