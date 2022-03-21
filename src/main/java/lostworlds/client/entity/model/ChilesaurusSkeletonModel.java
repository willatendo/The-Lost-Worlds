package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class ChilesaurusSkeletonModel extends TyrannomatedTyrannomationModel<FossilEntity> {
	private static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/model/entity/fossil/chilesaurus/skeleton.png");

	@Override
	public ResourceLocation getAnimationFileLocation(FossilEntity entity) {
		return LostWorldsUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilEntity entity) {
		return LostWorldsUtils.rL("geo/chilesaurus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilEntity entity) {
		return TEXTURE;
	}
}
