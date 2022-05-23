package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.cretaceous.Suchomimus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class SuchomimusModel extends AnimatedGeoModel<Suchomimus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Suchomimus entity) {
		return LostWorldsUtils.rL("animations/suchomimus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(Suchomimus entity) {
		return LostWorldsUtils.rL("geo/suchomimus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Suchomimus entity) {
		return LostWorldsUtils.rL("textures/model/entity/suchomimus/texture.png");
	}

	@Override
	public void setLivingAnimations(Suchomimus entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
