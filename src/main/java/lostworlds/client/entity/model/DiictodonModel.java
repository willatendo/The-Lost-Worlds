package lostworlds.client.entity.model;

import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

@OnlyIn(Dist.CLIENT)
public class DiictodonModel extends SpeciesTagModelAndTextureableModel<DiictodonEntity> {
	@Override
	public void setLivingAnimations(DiictodonEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
	}
}
