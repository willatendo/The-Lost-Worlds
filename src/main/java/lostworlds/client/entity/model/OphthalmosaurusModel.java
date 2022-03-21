package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.jurassic.OphthalmosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class OphthalmosaurusModel extends TyrannomatedTyrannomationModel<OphthalmosaurusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("animations/ophthalmosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("geo/ophthalmosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(OphthalmosaurusEntity entity) {
		return LostWorldsUtils.rL("textures/model/entity/ophthalmosaurus/texture.png");
	}

//	@Override
//	public void setLivingAnimations(OphthalmosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) 
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
