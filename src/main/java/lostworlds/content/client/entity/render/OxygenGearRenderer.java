package lostworlds.content.client.entity.render;

import lostworlds.content.client.entity.model.OxygenGearModel;
import lostworlds.library.item.armour.OxygenArmorItem;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationArmorRenderer;

public class OxygenGearRenderer extends TyrannomationArmorRenderer<OxygenArmorItem>
{
	public OxygenGearRenderer() 
	{
		super(new OxygenGearModel());
		this.headBone = "helmet";
		this.bodyBone = "chestplate";
		this.rightArmBone = "rightArm";
		this.leftArmBone = "leftArm";
		this.rightLegBone = "rightLeg";
		this.leftLegBone = "leftLeg";
		this.rightBootBone = "rightBoot";
		this.leftBootBone = "leftBoot";
	}
}
