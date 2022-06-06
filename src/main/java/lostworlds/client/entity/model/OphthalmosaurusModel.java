package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.jurassic.Ophthalmosaurus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OphthalmosaurusModel extends SpeciesTagModelAndTextureableModel<Ophthalmosaurus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Ophthalmosaurus entity) {
		return LostWorldsUtils.rL("animations/ophthalmosaurus.animations.json");
	}

//	@Override
//	public void setLivingAnimations(OphthalmosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) 
//	{
//		super.setLivingAnimations(entity, uniqueID, customPredicate);
//		IBone body = this.getAnimationProcessor().getBone("body");
//		
//		if(Entity.getHorizontalDistanceSqr(entity.getDeltaMovement()) > 1.0E-7D) 
//		{
//			body.setRotationX(-0.05F + -0.05F * MathHelper.cos(p_225597_4_ * 0.3F));
//		}
//	}
}
