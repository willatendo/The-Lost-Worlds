package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KentrosaurusSkeletonModel extends AnimatedGeoModel<FossilEntity>
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/model/entity/kentrosaurus/skeleton.png");

	@Override
	public ResourceLocation getAnimationFileLocation(FossilEntity entity) 
	{
		return ModUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilEntity entity) 
	{
		return ModUtils.rL("geo/kentrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilEntity entity) 
	{
		return TEXTURE;
	}
}
