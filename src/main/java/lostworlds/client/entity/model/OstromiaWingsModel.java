package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.Ostromia;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OstromiaWingsModel extends AnimatedGeoModel<Ostromia> {
	@Override
	public ResourceLocation getAnimationFileLocation(Ostromia entity) {
		return LostWorldsUtils.rL("animations/ostromia_wing.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(Ostromia entity) {
		return LostWorldsUtils.rL("geo/ostromia_wings.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Ostromia entity) {
		return LostWorldsUtils.rL("textures/model/entity/ostromia/wings_texture.png");
	}
}
