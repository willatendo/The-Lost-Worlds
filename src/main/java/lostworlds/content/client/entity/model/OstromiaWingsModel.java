package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class OstromiaWingsModel extends TyrannomatedTyrannomationModel<OstromiaEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(OstromiaEntity entity) {
		return ModUtils.rL("animations/ostromia_wing.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(OstromiaEntity entity) {
		return ModUtils.rL("geo/ostromia_wings.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OstromiaEntity entity) {
		return ModUtils.rL("textures/model/entity/ostromia/wings_texture.png");
	}
}
