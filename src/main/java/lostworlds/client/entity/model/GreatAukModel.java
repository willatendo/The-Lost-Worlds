package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.semiaquatic.modern.GreatAukEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class GreatAukModel extends TyrannomatedTyrannomationModel<GreatAukEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(GreatAukEntity entity) {
		return LostWorldsUtils.rL("animations/great_auk.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(GreatAukEntity entity) {
		return LostWorldsUtils.rL("geo/great_auk.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GreatAukEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/great_auk/texture.png");
	}

	@Override
	public void setLivingAnimations(GreatAukEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (entity.isInWaterOrBubble()) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
