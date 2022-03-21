package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class OstromiaWingsModel extends TyrannomatedTyrannomationModel<OstromiaEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(OstromiaEntity entity) {
		return LostWorldsUtils.rL("animations/ostromia_wing.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(OstromiaEntity entity) {
		return LostWorldsUtils.rL("geo/ostromia_wings.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OstromiaEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/ostromia/wings_texture.png");
	}
}
