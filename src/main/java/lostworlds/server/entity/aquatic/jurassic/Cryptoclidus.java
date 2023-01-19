package lostworlds.server.entity.aquatic.jurassic;

import java.util.List;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.aquatic.DolphinLike;
import lostworlds.server.species.SpeciesType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
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

public class Cryptoclidus extends DolphinLike implements IAnimatable, SpeciesTagModelAndTextureable {
	protected static final EntityDataAccessor<Byte> VARIENT = SynchedEntityData.defineId(Cryptoclidus.class, EntityDataSerializers.BYTE);
	private AnimationFactory factory = new AnimationFactory(this);

	public Cryptoclidus(EntityType<? extends Cryptoclidus> entity, Level level) {
		super(entity, level);
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return LostWorldsTags.ModSpeciesTypeTags.CRYPTOCLIDUS;
	}

	@Override
	public byte getVarientData() {
		return this.getVarient();
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cryptoclidus.swim", true));
			return PlayState.CONTINUE;
		} else {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cryptoclidus.idle", true));
			return PlayState.CONTINUE;
		}
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.cryptoclidusHeath.get()).add(Attributes.MOVEMENT_SPEED, LostWorldsConfig.COMMON_CONFIG.cryptoclidusSpeed.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.COMMON_CONFIG.cryptoclidusAttackDamage.get());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		byte varient;
		List<SpeciesType> types = LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get().tags().getTag(this.getTagToUse()).stream().toList();
		varient = (byte) this.random.nextInt(types.size());
		this.entityData.define(VARIENT, varient);
	}

	public byte getVarient() {
		return entityData.get(VARIENT);
	}

	public void setVarient(byte varient) {
		entityData.set(VARIENT, varient);
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
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return LostWorldsEntities.CRYPTOCLIDUS.create(serverLevel);
	}
}
