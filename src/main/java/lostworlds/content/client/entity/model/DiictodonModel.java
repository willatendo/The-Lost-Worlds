package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.permian.DiictodonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class DiictodonModel extends TyrannomatedTyrannomationModel<DiictodonEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(DiictodonEntity entity) {
		return ModUtils.rL("animations/diictodon.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(DiictodonEntity entity) {
		return ModUtils.rL("geo/diictodon.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DiictodonEntity entity) {
		return ModUtils.rL("textures/model/entity/diictodon/texture.png");
	}

	@Override
	public void setLivingAnimations(DiictodonEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
