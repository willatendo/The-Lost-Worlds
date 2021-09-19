package lostworlds.content.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import lostworlds.library.entity.prehistoric.jurassic.ChilesaurusEntity;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ChilesaurusModel extends PatternModel<ChilesaurusEntity>
{
	private static final ResourceLocation MALE_TEXTURE = ModUtils.rL("textures/model/entity/chilesaurus/male.png");
	private static final ResourceLocation FEMALE_TEXTURE = ModUtils.rL("textures/model/entity/chilesaurus/female.png");

	public ChilesaurusModel() 
	{
		super(Pair.of(ImmutableList.of(MALE_TEXTURE), ImmutableList.of(FEMALE_TEXTURE)));
	}

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
	public void setLivingAnimations(ChilesaurusEntity entity, Integer uniqueID, AnimationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(!entity.isEating())
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
