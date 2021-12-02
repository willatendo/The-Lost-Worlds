package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.permian.TetraceratopsEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.processor.IBone;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.model.provider.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class TetraceratopsModel extends TyrannomatedTyrannomationModel<TetraceratopsEntity>
{
	@Override
	public ResourceLocation getAnimationFileLocation(TetraceratopsEntity entity) 
	{
		return ModUtils.rL("animations/tetraceratops.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(TetraceratopsEntity entity) 
	{
		return ModUtils.rL("geo/tetraceratops.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(TetraceratopsEntity entity) 
	{
		return ModUtils.rL("textures/model/entity/tetraceratops/texture.png");
	}

	@Override
	public void setLivingAnimations(TetraceratopsEntity entity, Integer uniqueID, TyrannomationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(!(entity.isEating() || entity.isSleeping()))
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
