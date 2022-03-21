package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.permian.DimetrodonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class DimetrodonModel extends TyrannomatedTyrannomationModel<DimetrodonEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(DimetrodonEntity entity) {
		return LostWorldsUtils.rL("animations/dimetrodon.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(DimetrodonEntity entity) {
		return LostWorldsUtils.rL("geo/dimetrodon.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DimetrodonEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/dimetrodon/texture.png");
	}

	@Override
	public void setLivingAnimations(DimetrodonEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
