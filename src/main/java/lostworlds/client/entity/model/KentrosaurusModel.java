package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.KentrosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class KentrosaurusModel extends AnimatedGeoModel<KentrosaurusEntity> {
	private static final ResourceLocation TEXTURE_1 = LostWorldsUtils.rL("textures/model/entity/kentrosaurus/texture_1.png");
	private static final ResourceLocation TEXTURE_2 = LostWorldsUtils.rL("textures/model/entity/kentrosaurus/texture_2.png");
	public ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(KentrosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/kentrosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(KentrosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/kentrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(KentrosaurusEntity entity) {
		return this.texture;
	}

	@Override
	public void setLivingAnimations(KentrosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
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
