package lostworlds.library.entity.aquatic.permian;

import lostworlds.library.entity.aquatic.BasicFishLikeEntity;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.entity.EntityType;
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

public class PalaeoniscumEntity extends BasicFishLikeEntity implements ITyrannomatable {
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) {
		event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.palaeoniscum", true));
		return PlayState.CONTINUE;
	}

	public PalaeoniscumEntity(EntityType<? extends PalaeoniscumEntity> entity, World world) {
		super(entity, world);
	}

	@Override
	protected ItemStack getBucketItemStack() {
		return DinoTypes.PALAEONISCUM.getFishBucket().getDefaultInstance();
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
