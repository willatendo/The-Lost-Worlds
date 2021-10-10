package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChilesaurusSkeletonModel extends AnimatedGeoModel<FossilEntity>
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/model/entity/fossil/chilesaurus/skeleton.png");

	@Override
	public ResourceLocation getAnimationFileLocation(FossilEntity entity) 
	{
		return ModUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilEntity entity) 
	{
		return ModUtils.rL("geo/chilesaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilEntity entity) 
	{
		return TEXTURE;
	}
}
