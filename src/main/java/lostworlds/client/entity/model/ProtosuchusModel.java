package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.semiaquatic.jurassic.ProtosuchusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ProtosuchusModel extends AnimatedGeoModel<ProtosuchusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(ProtosuchusEntity entity) {
		return LostWorldsUtils.rL("animations/protosuchus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(ProtosuchusEntity entity) {
		return LostWorldsUtils.rL("geo/protosuchus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ProtosuchusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/protosuchus/texture.png");
	}

	@Override
	public void setLivingAnimations(ProtosuchusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
