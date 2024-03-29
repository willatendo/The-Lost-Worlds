package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.cretaceous.ParksosaurusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ParksosaurusModel extends AnimatedGeoModel<ParksosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(ParksosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/parksosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(ParksosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/parksosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ParksosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/parksosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(ParksosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
