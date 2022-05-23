package lostworlds.server.entity.aquatic.jurassic;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.aquatic.DolphinLike;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Ophthalmosaurus extends DolphinLike implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);

	public Ophthalmosaurus(EntityType<? extends DolphinLike> entity, Level world) {
		super(entity, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ophthalmosaurus.swim", true));
			return PlayState.CONTINUE;
		} else {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ophthalmosaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusHeath.get()).add(Attributes.MOVEMENT_SPEED, LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpeed.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusAttackDamage.get());
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
		return LostWorldsEntities.OPHTHALMOSAURUS.create(world);
	}
}
