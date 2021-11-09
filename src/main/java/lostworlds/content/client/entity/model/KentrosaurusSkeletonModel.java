package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class KentrosaurusSkeletonModel extends TyrannomatedTyrannomationModel<FossilEntity>
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/model/entity/fossil/kentrosaurus/skeleton.png");

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
