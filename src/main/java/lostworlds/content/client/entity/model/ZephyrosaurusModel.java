package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.cretaceous.ZephyrosaurusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class ZephyrosaurusModel extends TyrannomatedTyrannomationModel<ZephyrosaurusEntity>
{
	private static final ResourceLocation BABY_TEXTURE = ModUtils.rL("textures/model/entity/zephyrosaurus/baby.png");
	private static final ResourceLocation MALE_TEXTURE = ModUtils.rL("textures/model/entity/zephyrosaurus/male.png");
	private static final ResourceLocation FEMALE_TEXTURE = ModUtils.rL("textures/model/entity/zephyrosaurus/female.png");
	private ResourceLocation texture;

	@Override
	public ResourceLocation getAnimationFileLocation(ZephyrosaurusEntity entity) 
	{
		return ModUtils.rL("animations/zephyrosaurus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(ZephyrosaurusEntity entity) 
	{
		return ModUtils.rL("geo/zephyrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ZephyrosaurusEntity entity) 
	{
		return this.texture;
	}

	@Override
	public void setLivingAnimations(ZephyrosaurusEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(!(entity.isEating() || entity.isSleeping()))
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
		
		if(entity.isBaby())
		{
			this.texture = this.BABY_TEXTURE;
		}
		if(entity.getSex() == 0 && !entity.isBaby())
		{
			this.texture = this.MALE_TEXTURE;
		}
		if(entity.getSex() == 1 && !entity.isBaby())
		{
			this.texture = this.FEMALE_TEXTURE;
		}
	}
}
