package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.item.armour.OxygenArmorItem;
import net.minecraft.util.ResourceLocation;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

public class OxygenGearModel extends TyrannomatedTyrannomationModel<OxygenArmorItem>
{
	@Override
	public ResourceLocation getAnimationFileLocation(OxygenArmorItem animatable) 
	{
		return ModUtils.rL("animations/null.json");
	}

	@Override
	public ResourceLocation getModelLocation(OxygenArmorItem object) 
	{
		return ModUtils.rL("geo/oxygen_gear.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OxygenArmorItem object) 
	{
		return new ResourceLocation("textures/models/armor/oxygen_gear_layer_1.png");
	}
}
