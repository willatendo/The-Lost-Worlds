package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.cretaceous.ZephyrosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class ZephyrosaurusModel extends TyrannomatedTyrannomationModel<ZephyrosaurusEntity> {
	private static final ResourceLocation TEXTURE_1 = ModUtils.rL("textures/model/entity/zephyrosaurus/texture_1.png");
	private static final ResourceLocation TEXTURE_2 = ModUtils.rL("textures/model/entity/zephyrosaurus/texture_2.png");
	private ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(ZephyrosaurusEntity entity) {
		return ModUtils.rL("animations/zephyrosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(ZephyrosaurusEntity entity) {
		return ModUtils.rL("geo/zephyrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ZephyrosaurusEntity entity) {
		return this.texture;
	}

	@Override
	public void setLivingAnimations(ZephyrosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
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
