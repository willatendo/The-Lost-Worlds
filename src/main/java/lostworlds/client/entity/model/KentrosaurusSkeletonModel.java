package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class KentrosaurusSkeletonModel extends AnimatedGeoModel<FossilEntity> {
	private static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/model/entity/fossil/kentrosaurus/skeleton.png");

	@Override
	public ResourceLocation getAnimationFileLocation(FossilEntity entity) {
		return LostWorldsUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilEntity entity) {
		return LostWorldsUtils.rL("geo/kentrosaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilEntity entity) {
		return TEXTURE;
	}
}
