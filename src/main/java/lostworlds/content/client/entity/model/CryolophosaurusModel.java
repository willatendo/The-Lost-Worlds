package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.jurassic.CryolophosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class CryolophosaurusModel extends TyrannomatedTyrannomationModel<CryolophosaurusEntity>
{
	private static final ResourceLocation TEXTURE_1 = ModUtils.rL("textures/model/entity/cryolophosaurus/texture_1.png");
	private static final ResourceLocation TEXTURE_2 = ModUtils.rL("textures/model/entity/cryolophosaurus/texture_2.png");
	private ResourceLocation texture;
	
	@Override
	public ResourceLocation getAnimationFileLocation(CryolophosaurusEntity entity) 
	{
		return ModUtils.rL("animations/cryolophosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(CryolophosaurusEntity entity) 
	{
		return ModUtils.rL("geo/cryolophosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CryolophosaurusEntity entity) 
	{
		return texture;
	}

	@Override
	public void setLivingAnimations(CryolophosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(!(entity.isEating() || entity.isSleeping()))
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
		
		if(entity.getVarient() == 0)
		{
			this.texture = this.TEXTURE_1;
		}
		if(entity.getVarient() == 1)
		{
			this.texture = this.TEXTURE_2;
		}
	}
}
