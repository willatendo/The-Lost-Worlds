package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.cretaceous.PsittacosaurusEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class PsittacosaurusModel extends AnimatedGeoModel<PsittacosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(PsittacosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/psittacosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(PsittacosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/psittacosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PsittacosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/psittacosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(PsittacosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
