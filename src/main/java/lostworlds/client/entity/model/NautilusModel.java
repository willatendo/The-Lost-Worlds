package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.modern.NautilusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NautilusModel extends AnimatedGeoModel<NautilusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(NautilusEntity entity) {
		return LostWorldsUtils.rL("animations/nautilus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(NautilusEntity entity) {
		return LostWorldsUtils.rL("geo/nautilus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(NautilusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/nautilus/texture.png");
	}
}
