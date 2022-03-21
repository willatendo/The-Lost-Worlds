package lostworlds.server.entity.aquatic.cambrian;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.entity.aquatic.BasicFishLikeEntity;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class AnomalocarisEntity extends BasicFishLikeEntity implements ITyrannomatable {
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) {
		event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.anomalocaris", true));
		return PlayState.CONTINUE;
	}

	public AnomalocarisEntity(EntityType<? extends AnomalocarisEntity> entity, World world) {
		super(entity, world);
	}

	public static AttributeModifierMap createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.SERVER_CONFIG.anomalocarisHeath.get()).add(Attributes.ATTACK_DAMAGE, LostWorldsConfig.SERVER_CONFIG.anomalocarisAttackDamage.get()).build();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected ItemStack getBucketItemStack() {
		return DinoTypes.ANOMALOCARIS.getFishBucket().getDefaultInstance();
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	@Override
	public void registerControllers(TyrannomationData data) {
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() {
		return this.factory;
	}
}
