package lostworlds.library.item.armour;

import lostworlds.content.ModUtils;
import net.minecraft.inventory.EquipmentSlotType;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;
import tyrannotitanlib.library.tyrannomation.item.TyrannomationArmorItem;

public class OxygenArmorItem extends TyrannomationArmorItem implements ITyrannomatable
{
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	public OxygenArmorItem(EquipmentSlotType slot) 
	{
		super(ModArmourMaterial.OXYGEN_GEAR, slot, new Properties().tab(ModUtils.ITEMS));
	}
	
	private <P extends ITyrannomatable> PlayState predicate(TyrannomationEvent<P> event) 
	{
		event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.oxygen_gear.null", true));
		return PlayState.STOP;
	}

	@Override
	public void registerControllers(TyrannomationData data) 
	{
		data.addAnimationController(new TyrannomationController(this, "controller", 20, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() 
	{
		return this.factory;
	}
}
