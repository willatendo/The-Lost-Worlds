package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.cretaceous.GiganotosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class GiganotosaurusModel extends TyrannomatedTyrannomationModel<GiganotosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(GiganotosaurusEntity entity) {
		return ModUtils.rL("animations/giganotosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(GiganotosaurusEntity entity) {
		return ModUtils.rL("geo/giganotosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GiganotosaurusEntity entity) {
		return ModUtils.rL("textures/model/entity/giganotosaurus/texture.png");
	}

	@Override
	public void setLivingAnimations(GiganotosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
