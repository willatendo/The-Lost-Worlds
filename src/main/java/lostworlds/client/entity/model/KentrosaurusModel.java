package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.KentrosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class KentrosaurusModel extends TyrannomatedTyrannomationModel<KentrosaurusEntity> {
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
	public void setLivingAnimations(KentrosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
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
