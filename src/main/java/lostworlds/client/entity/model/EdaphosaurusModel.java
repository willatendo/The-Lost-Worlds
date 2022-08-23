package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.permian.EdaphosaurusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EdaphosaurusModel extends AnimatedGeoModel<EdaphosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(EdaphosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/edaphosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(EdaphosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/edaphosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EdaphosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/edaphosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(EdaphosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
