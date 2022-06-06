package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.permian.Palaeoniscum;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

@OnlyIn(Dist.CLIENT)
public class PalaeoniscumModel extends SpeciesTagModelAndTextureableModel<Palaeoniscum> {
	@Override
	public ResourceLocation getAnimationFileLocation(Palaeoniscum entity) {
		return LostWorldsUtils.rL("animations/palaeoniscum.animations.json");
	}

	@Override
	public void setLivingAnimations(Palaeoniscum entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
