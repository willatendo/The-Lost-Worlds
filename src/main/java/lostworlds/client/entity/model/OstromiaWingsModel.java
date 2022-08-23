package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OstromiaWingsModel extends AnimatedGeoModel<OstromiaEntity> {
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
