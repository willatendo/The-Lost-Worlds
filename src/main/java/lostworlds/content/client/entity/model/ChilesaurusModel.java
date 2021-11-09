package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.jurassic.ChilesaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class ChilesaurusModel extends TyrannomatedTyrannomationModel<ChilesaurusEntity>
{
	private static final ResourceLocation MALE_TEXTURE = ModUtils.rL("textures/model/entity/chilesaurus/male.png");
	private static final ResourceLocation FEMALE_TEXTURE = ModUtils.rL("textures/model/entity/chilesaurus/female.png");
	private ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(ChilesaurusEntity entity) 
	{
		return ModUtils.rL("animations/chilesaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(ChilesaurusEntity entity) 
	{
		return ModUtils.rL("geo/chilesaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ChilesaurusEntity entity) 
	{
		return this.texture;
	}

	@Override
	public void setLivingAnimations(ChilesaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) 
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
