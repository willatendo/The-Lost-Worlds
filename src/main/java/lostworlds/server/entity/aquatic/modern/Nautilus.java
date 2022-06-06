package lostworlds.server.entity.aquatic.modern;

import java.util.List;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.aquatic.BasicFishLikeMob;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.species.SpeciesType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
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

public class Nautilus extends BasicFishLikeMob implements IAnimatable, SpeciesTagModelAndTextureable {
	protected static final EntityDataAccessor<Byte> VARIENT = SynchedEntityData.defineId(Nautilus.class, EntityDataSerializers.BYTE);
	private AnimationFactory factory = new AnimationFactory(this);

	public Nautilus(EntityType<? extends PathfinderMob> entity, Level world) {
		super(entity, world);
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return LostWorldsTags.ModSpeciesTypeTags.NAUTILUS;
	}

	@Override
	public byte getVarientData() {
		return this.getVarient();
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.nautilus", true));
		return PlayState.CONTINUE;
	}

	public static Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.nautilusHeath.get()).add(Attributes.ARMOR, LostWorldsConfig.COMMON_CONFIG.nautilusArmour.get());
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
