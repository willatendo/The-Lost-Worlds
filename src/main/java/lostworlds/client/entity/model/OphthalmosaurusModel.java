package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.jurassic.OphthalmosaurusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OphthalmosaurusModel extends AnimatedGeoModel<OphthalmosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/ophthalmosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/ophthalmosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/ophthalmosaurus/texture.png");
	}
}
