package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.jurassic.Ophthalmosaurus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class OphthalmosaurusModel extends AnimatedGeoModel<Ophthalmosaurus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Ophthalmosaurus entity) {
		return LostWorldsUtils.rL("animations/ophthalmosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(Ophthalmosaurus entity) {
		return LostWorldsUtils.rL("geo/ophthalmosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Ophthalmosaurus entity) {
		return LostWorldsUtils.rL("textures/model/entity/ophthalmosaurus/texture.png");
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
