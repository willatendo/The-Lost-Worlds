package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.triassic.EoraptorEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class EoraptorModel extends TyrannomatedTyrannomationModel<EoraptorEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(EoraptorEntity entity) {
		return LostWorldsUtils.rL("animations/eoraptor.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(EoraptorEntity entity) {
		return LostWorldsUtils.rL("geo/eoraptor.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EoraptorEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/eoraptor/texture.png");
	}

	@Override
	public void setLivingAnimations(EoraptorEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (!(entity.isEating() || entity.isSleeping())) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
