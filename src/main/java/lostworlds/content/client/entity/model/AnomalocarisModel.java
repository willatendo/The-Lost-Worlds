package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.aquatic.cambrian.AnomalocarisEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisModel extends TyrannomatedTyrannomationModel<AnomalocarisEntity>
{
	@Override
	public ResourceLocation getAnimationFileLocation(AnomalocarisEntity entity) 
	{
		return ModUtils.rL("animations/anomalocaris.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(AnomalocarisEntity entity) 
	{
		return ModUtils.rL("geo/anomalocaris.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(AnomalocarisEntity entity) 
	{
		return ModUtils.rL("textures/model/entity/anomalocaris/texture.png");
	}
}
