package lostworlds.server.entity.aquatic.jurassic;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.aquatic.DolphinLikeEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class OphthalmosaurusEntity extends DolphinLikeEntity implements ITyrannomatable {
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.ophthalmosaurus.swim", true));
			return PlayState.CONTINUE;
		} else {
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.ophthalmosaurus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public OphthalmosaurusEntity(EntityType<? extends DolphinLikeEntity> entity, World world) {
		super(entity, world);
	}

	public static AttributeModifierMap createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusHeath.get()).add(Attributes.MOVEMENT_SPEED, LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusSpeed.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.SERVER_CONFIG.ophthalmosaurusAttackDamage.get()).build();
	}

	@Override
	public void registerControllers(TyrannomationData data) {
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() {
		return this.factory;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		return LostWorldsEntities.OPHTHALMOSAURUS.create(world);
	}
}
