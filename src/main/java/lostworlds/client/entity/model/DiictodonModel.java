package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DiictodonModel extends AnimatedGeoModel<DiictodonEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(DiictodonEntity entity) {
		return LostWorldsUtils.rL("animations/diictodon.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(DiictodonEntity entity) {
		return LostWorldsUtils.rL("geo/diictodon.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DiictodonEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/diictodon/texture.png");
	}

	@Override
	public void setLivingAnimations(DiictodonEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
