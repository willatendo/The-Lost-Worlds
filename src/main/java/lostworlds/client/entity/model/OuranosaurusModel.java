package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.cretaceous.OuranosaurusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class OuranosaurusModel extends AnimatedGeoModel<OuranosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(OuranosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/ouranosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(OuranosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/ouranosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OuranosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/ouranosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(OuranosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
