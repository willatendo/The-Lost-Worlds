package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class PalaeoniscumModel extends AnimatedGeoModel<PalaeoniscumEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("animations/palaeoniscum.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("geo/palaeoniscum.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PalaeoniscumEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/palaeoniscum/texture.png");
	}

	@Override
	public void setLivingAnimations(PalaeoniscumEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
