package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.jurassic.Cryptoclidus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CryptoclidusModel extends SpeciesTagModelAndTextureableModel<Cryptoclidus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Cryptoclidus entity) {
		return LostWorldsUtils.rL("animations/cryptoclidus.animations.json");
	}

//	@Override
//	public void setLivingAnimations(Cryptoclidus entity, Integer uniqueID, AnimationEvent customPredicate) {
//		super.setLivingAnimations(entity, uniqueID, customPredicate);
//		IBone head = this.getAnimationProcessor().getBone("neck");
//
//		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
//		if (!(entity.isEating() || entity.isSleeping())) {
//			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
//			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
//		}
//	}
}
