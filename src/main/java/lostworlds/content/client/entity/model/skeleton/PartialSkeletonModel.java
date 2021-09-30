package lostworlds.content.client.entity.model.skeleton;

import lostworlds.content.ModUtils;
import lostworlds.library.item.FossilItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class PartialSkeletonModel extends AnimatedGeoModel<FossilItem>
{
	private final String model;
	
	public PartialSkeletonModel(String modelAndTexture) 
	{
		this.model = modelAndTexture;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(FossilItem animatable) 
	{
		return ModUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilItem item) 
	{
		return ModUtils.rL("geo/" + model + ".geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilItem item) 
	{
		return ModUtils.rL("textures/model/entity/" + model + "/skeleton.png");
	}
}
