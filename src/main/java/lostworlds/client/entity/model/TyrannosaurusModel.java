package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.cretaceous.TyrannosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class TyrannosaurusModel extends AnimatedGeoModel<TyrannosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(TyrannosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/tyrannosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(TyrannosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/tyrannosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(TyrannosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/tyrannosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(TyrannosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
