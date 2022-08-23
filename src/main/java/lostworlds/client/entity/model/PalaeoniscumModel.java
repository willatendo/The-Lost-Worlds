package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PalaeoniscumModel extends AnimatedGeoModel<PalaeoniscumEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("animations/palaeoniscum.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("geo/palaeoniscum.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/palaeoniscum/texture.png");
	}
}
