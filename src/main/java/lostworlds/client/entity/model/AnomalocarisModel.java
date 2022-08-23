package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.cambrian.AnomalocarisEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnomalocarisModel extends AnimatedGeoModel<AnomalocarisEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(AnomalocarisEntity entity) {
		return LostWorldsUtils.rL("animations/anomalocaris.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(AnomalocarisEntity entity) {
		return LostWorldsUtils.rL("geo/anomalocaris.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(AnomalocarisEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/anomalocaris/texture.png");
	}
}
