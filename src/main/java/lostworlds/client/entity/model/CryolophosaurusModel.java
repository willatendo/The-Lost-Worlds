package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.CryolophosaurusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CryolophosaurusModel extends AnimatedGeoModel<CryolophosaurusEntity> {
	private static final ResourceLocation TEXTURE_1 = LostWorldsUtils.rL("textures/model/entity/cryolophosaurus/texture_1.png");
	private static final ResourceLocation TEXTURE_2 = LostWorldsUtils.rL("textures/model/entity/cryolophosaurus/texture_2.png");
	private ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(CryolophosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/cryolophosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(CryolophosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/cryolophosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CryolophosaurusEntity entity) {
		return texture;
	}

	@Override
	public void setLivingAnimations(CryolophosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}

		if (entity.getVarient() == 0) {
			this.texture = this.TEXTURE_1;
		}
		if (entity.getVarient() == 1) {
			this.texture = this.TEXTURE_2;
		}
	}
}
