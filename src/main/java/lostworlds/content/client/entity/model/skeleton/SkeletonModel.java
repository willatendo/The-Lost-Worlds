package lostworlds.content.client.entity.model.skeleton;

import lostworlds.content.ModUtils;
import lostworlds.library.item.FossilItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class SkeletonModel extends AnimatedGeoModel<FossilItem>
{
	private final String model;
	private final String texture;
	
	public SkeletonModel(String model, String texture) 
	{
		this.model = model;
		this.texture = texture;
	}
	
	public SkeletonModel(String modelAndTexture) 
	{
		this.model = modelAndTexture;
		this.texture = modelAndTexture;
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
		return ModUtils.rL("textures/model/entity/" + texture + "/skeleton.png");
	}
}
