package lostworlds.server.entity.avian;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class Pterosaur extends EggLayingMob implements IAnimatable {
	public static final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
	public static final AnimationBuilder SWIM_ANIMATION = new AnimationBuilder().addAnimation("into_swim").addAnimation("swim", true).addAnimation("out_of_swim");
	public static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
	public static final AnimationBuilder FLY_ANIMATION = new AnimationBuilder().addAnimation("into_fly").addAnimation("fly", true).addAnimation("out_of_fly");
	public static final AnimationBuilder SLEEP_ANIMATION = new AnimationBuilder().addAnimation("into_sleep").addAnimation("sleep", true).addAnimation("out_of_sleep");
	public static final AnimationBuilder EAT_ANIMATION = new AnimationBuilder().addAnimation("eat", false);
	public static final AnimationBuilder WALL_WALK_ANIMATION = new AnimationBuilder().addAnimation("into_wall_walk").addAnimation("wall_walk", true).addAnimation("out_of_wall_walk");

	public Pterosaur(EntityType<? extends Pterosaur> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void registerControllers(AnimationData data) {
	}

	@Override
	public AnimationFactory getFactory() {
		return null;
	}
}
