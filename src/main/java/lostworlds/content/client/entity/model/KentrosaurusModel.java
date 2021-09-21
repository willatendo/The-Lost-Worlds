package lostworlds.content.client.entity.model;

import lostworlds.library.entity.terrestrial.jurassic.KentrosaurusEntity;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class KentrosaurusModel extends AnimatedGeoModel<KentrosaurusEntity>
{
	private static final ResourceLocation MALE_TEXTURE = ModUtils.rL("textures/model/entity/kentrosaurus/male.png");
	private static final ResourceLocation FEMALE_TEXTURE = ModUtils.rL("textures/model/entity/kentrosaurus/female.png");
	private ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(KentrosaurusEntity entity) 
	{
		return ModUtils.rL("animations/kentrosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(KentrosaurusEntity entity) 
	{
		return ModUtils.rL("geo/kentrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(KentrosaurusEntity entity) 
	{
		return this.texture;
	}
	
	@Override
	public void setLivingAnimations(KentrosaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(!(entity.isEating() || entity.isSleeping()))
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
		
		if(entity.getSex() == 0)
		{
			this.texture = this.MALE_TEXTURE;
		}
		if(entity.getSex() == 1)
		{
			this.texture = this.FEMALE_TEXTURE;
		}
	}
}
