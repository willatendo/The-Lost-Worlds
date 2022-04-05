package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.permian.TetraceratopsEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class TetraceratopsModel extends AnimatedGeoModel<TetraceratopsEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(TetraceratopsEntity entity) {
		return LostWorldsUtils.rL("animations/tetraceratops.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(TetraceratopsEntity entity) {
		return LostWorldsUtils.rL("geo/tetraceratops.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(TetraceratopsEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/tetraceratops/texture.png");
	}

	@Override
	public void setLivingAnimations(TetraceratopsEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
