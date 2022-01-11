package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.aquatic.permian.PalaeoniscumEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class PalaeoniscumModel extends TyrannomatedTyrannomationModel<PalaeoniscumEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(PalaeoniscumEntity entity) {
		return ModUtils.rL("animations/palaeoniscum.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(PalaeoniscumEntity entity) {
		return ModUtils.rL("geo/palaeoniscum.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PalaeoniscumEntity entity) {
		return ModUtils.rL("textures/model/entity/palaeoniscum/texture.png");
	}

	@Override
	public void setLivingAnimations(PalaeoniscumEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
